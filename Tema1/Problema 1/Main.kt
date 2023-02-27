import java.util.*
import java.util.ArrayDeque

fun pow(base: Float, exponent: Int) : Float {
    var exponent1 = exponent
    var rezultat: Float = 1f

    while (exponent1 != 0) {
        rezultat *= base.toLong()
        --exponent1
    }
    return rezultat
}

fun isDigit(d : Char) : Int
{
    val numere = "1234567890"
    var esteNumar : Int = 0
    for(i in 0 .. numere.length - 1)
    {
        if(d == numere[i])
            esteNumar = 1
    }
    return esteNumar;
}

fun isOperator(d : Char) : Int
{
    val operatori = "+-/*^"
    var esteOperator = 0
    for(i in 0 .. operatori.length - 1)
    {
        if(d == operatori[i])
            esteOperator = 1
    }
    return esteOperator
}
fun operation(a: Float, b: Float, op: Char) : Float
{
    if(op == '+')
        return b+a;
    else if(op == '-')
        return b-a;
    else if(op == '/')
        return b/a;
    else if(op == '*')
        return b*a;
    else if(op == '^')
        return pow(b,a.toInt()); //find b^a
    return -1f
}
fun operatorPrio(d : Char) : Int
{
    if(d.toString() == "+" || d.toString() == "-")
        return 1
    if(d.toString() == "*" || d.toString() == "/")
        return 2
    if(d.toString() == "^")
        return 3
    return 0
}
fun main(args: Array<String>) {
    val str = readln()
    var plStr = ""
    var nStr = ""
    var i = 0
    while(i <= str.length - 1)
    {
        if(isDigit(str[i]) == 1)
        {
            plStr += " "
            plStr += str[i]
            if(i+1 <= str.length - 1)
                while(isDigit(str[i+1]) == 1)
                {
                    i++
                    plStr += str[i]
                }
        }
        if(str[i].toString() == "(")
        {
            nStr += str[i]
        }
        if(str[i].toString() == ")")
        {
            while(nStr.isNotEmpty() && nStr[nStr.length - 1].toString() != "(")
            {
                plStr += nStr[nStr.length - 1]
                nStr = nStr.dropLast(1)
            }
            nStr = nStr.dropLast(1)
        }
        if(isOperator(str[i]) == 1)
        {
            while(nStr.isNotEmpty() && (operatorPrio(nStr[nStr.length - 1]) >= operatorPrio(str[i])))
            {
                plStr += nStr[nStr.length - 1]
                nStr = nStr.dropLast(1)
            }
            nStr += str[i]
        }
        i++
    }

    while(nStr.isNotEmpty())
    {
        if(nStr[nStr.length - 1].toString() != "(")
            plStr += nStr[nStr.length - 1]
        nStr = nStr.dropLast(1)
    }
    var c : Float = 0f
    var d : Float = 0f
    var t = 0
    var stack = ArrayDeque<Float>()
    while(t <= plStr.length - 1)
    {
        if(isOperator(plStr[t]) == 1)
        {
            c = stack.pop()
            d = stack.pop()
            stack.push(operation(c, d, plStr[t]))
        }
        else if(plStr[t].toString() == " ")
        {
            var k : Float = 0f
            t++
            while(isDigit(plStr[t]) == 1) {
                k *= 10
                k += plStr[t].digitToInt()
                t++
            }
            stack.push(k)
            t--
        }
        t++
    }
    c = stack.pop()
    print(c)
}
