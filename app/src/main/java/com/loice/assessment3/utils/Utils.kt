package com.loice.assessment3.utils

import java.text.DecimalFormat

class Utils {
    companion object{
        fun formatCurrency(amount:Double):String{
            val fmt=DecimalFormat("KES #,###")
            return fmt.format(amount)

        }
    }
}