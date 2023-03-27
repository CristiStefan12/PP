// C Program for Message Queue (Writer Process)
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <regex.h>
#include <string.h>
// structure for message queue
struct mesg_buffer
{
    long mesg_type;
    char mesg_text[10000];
} message;

int verify_html(const char *txt)
{
    regex_t regex;
    int result;

    const char *regex_string = "^\\s*<\\s*[!a-zA-Z]+\\s*[^>]*>";

    result = regcomp(&regex, regex_string, REG_EXTENDED);
    if (result)
    {

        fprintf(stderr, "Failed to compile\n");
        return 0;
    }

    result = regexec(&regex, txt, 0, NULL, 0);

    regfree(&regex);

    return (result == 0);
}

int main()
{
    // gcc receiver.c -o receiver
    // ./receiver
    key_t key = -1;
    int msgid;
    // ftok to generate unique key
    // key = ftok("message_queue_name", 'B');
    // msgget creates a message queue
    // and returns identifier
    msgid = msgget(key, 0666 | IPC_CREAT);
    // msgrcv to receive message
    msgrcv(msgid, &message, sizeof(message), 1, 0);
    while (1)
    {
        msgrcv(msgid, &message, sizeof(message), 1, 0);

        if (strlen(message.mesg_text) == 0)
        {
            break;
        }

        if (verify_html(message.mesg_text)) {
            FILE* html_msg = fopen("index.html", "w");
            fputs(message.mesg_text, html_msg);
            fclose(html_msg);
            printf("The msg is html\n");
        } else {
            printf("The msg isn't html\n");
        }
    }

        // to destroy the message queue
        msgctl(msgid, IPC_RMID, NULL);
        return 0;
    }