package com.example.flextest

data class Data(val id: Int, val name: String)


object Fields{
    val NAME = Data(1,"Name")
    val INTEGER = Data(2,"Integer")
    val NUMERIC = Data(3,"Numeric")
    val DATE = Data(4,"Date")
    val RANDOM = Data(5,"Random")
    val DESCRIPTION = Data(6,"Description")
    val SWITCH = Data(7,"Switch")
    val YESNO = Data(8,"Yes/No")
    val BARCODE = Data(9,"Barcode")
    val RADIO = Data(10,"Radio")
    val DROPDOWN = Data(11,"Dropdown")
    val MULTICHOICE = Data(12,"Multi Choice")
    val TIME = Data(13,"Time")

}