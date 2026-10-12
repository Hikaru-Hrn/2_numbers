package com.example.a2_numbers

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNumA: EditText
    private lateinit var etNumB: EditText
    private lateinit var btnAdd: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNumA = findViewById(R.id.numA)
        etNumB = findViewById(R.id.numB)
        btnAdd = findViewById(R.id.btnAdd)
        tvResult = findViewById(R.id.tvResult)

        btnAdd.setOnClickListener {
            calculateSum()
        }
    }

    private fun calculateSum() {
        val rawA = etNumA.text.toString().trim()
        val rawB = etNumB.text.toString().trim()

        // -----------------------------------------------------------------
        // TODO 1: Проверка на пустые значения в полях numA и numB
        // -----------------------------------------------------------------
        var hasEmptyField = false

        if (rawA.isEmpty()) {
            etNumA.error = "Поле не должно быть пустым"
            hasEmptyField = true
        }

        if (rawB.isEmpty()) {
            etNumB.error = "Поле не должно быть пустым"
            hasEmptyField = true
        }

        if (hasEmptyField) {
            Toast.makeText(this, "Пожалуйста, заполните оба поля", Toast.LENGTH_SHORT).show()
            return
        }

        // -----------------------------------------------------------------
        // TODO 3: Поддержка вещественных (не целых) чисел
        // Заменяем запятую на точку для совместимости с различными клавиатурами
        // -----------------------------------------------------------------
        val numA = rawA.replace(',', '.').toDoubleOrNull()
        val numB = rawB.replace(',', '.').toDoubleOrNull()

        if (numA == null) {
            etNumA.error = "Некорректный формат числа"
            return
        }

        if (numB == null) {
            etNumB.error = "Некорректный формат числа"
            return
        }

        val sum = numA + numB

        // Форматирование: если число целое (например, 5.0), выводим как 5
        val displayResult = if (sum % 1.0 == 0.0) {
            sum.toLong().toString()
        } else {
            // Округляем до разумного числа знаков для устранения погрешностей IEEE 754
            String.format(java.util.Locale.US, "%.6f", sum).trimEnd('0').trimEnd('.')
        }

        tvResult.text = "Результат: $displayResult"
    }
}