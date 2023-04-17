package dev.cancio.gestor.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.cancio.gestor.domain.Transaction
import java.io.IOException

class TransactionRepository {
    fun getTransactionsFromJson(context: Context):List<Transaction> {
        val jsonFileString = getJsonDataFromAsset(context, "data.json")

        val gson = Gson()
        val listTransactionType = object : TypeToken<List<Transaction>>() {}.type
        val values: List<Transaction> = gson.fromJson(jsonFileString, listTransactionType)
        Log.i("tansactions",values.toString())
        return values
    }


    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}