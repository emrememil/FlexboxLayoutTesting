package com.example.flextest

data class Data(val id: String, val name: String)


object Fields{
    val NAME = Data("name","Name")
    val INTEGER = Data("integer","Integer")
    val NUMERIC = Data("numeric","Numeric")
    val DATE = Data("date","Date")
    val RANDOM = Data("random","Random")
    val DESCRIPTION = Data("description","Description")
    val SWITCH = Data("switch","Switch")
    val YESNO = Data("yes_no","Yes/No")
    val BARCODE = Data("barcode","Barcode")
    val RADIO = Data("radio","Radio")
    val DROPDOWN = Data("dropdown","Dropdown")
    val MULTICHOICE = Data("multi_choice","Multi Choice")
    val TIME = Data("time","Time")

}