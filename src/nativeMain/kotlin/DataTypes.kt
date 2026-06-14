typealias SByte = UByte
typealias SWord = UShort

fun UInt.toSByte(): SByte = toUByte()
fun UInt.toSWord(): SWord = toUShort()

fun SByte.toSWord(): SWord = toUShort()
fun SWord.toSByte(): SByte = toUByte()

fun Boolean.toSByte(): SByte = if (this) 1u else 0u
fun SByte.toBoolean(): Boolean = this > 0u

infix fun SByte.shr(bitCount: Int): SByte = toUInt().shr(bitCount).toSByte()
infix fun SByte.shl(bitCount: Int): SByte = toUInt().shl(bitCount).toSByte()

infix fun SWord.shr(bitCount: Int): SWord = toUInt().shr(bitCount).toSWord()
infix fun SWord.shl(bitCount: Int): SWord = toUInt().shl(bitCount).toSWord()

fun SByte.hex(): String = toHexString(HexFormat { upperCase = true })
fun SWord.hex(): String = toHexString(HexFormat { upperCase = true })

fun SByte.checkBit(n: Int): Boolean = ((this shr n) and 1u) > 0u
fun SByte.setBit(n: Int, value: Boolean): SByte =
    (this and (1u.toSByte() shl (n - 1)).inv()) or (value.toSByte())