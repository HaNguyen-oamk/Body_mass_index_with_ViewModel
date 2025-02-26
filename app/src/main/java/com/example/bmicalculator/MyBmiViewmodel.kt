import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyBmiViewModel : ViewModel() {
    private val _heightInput = MutableStateFlow("")
    val heightInput: StateFlow<String> = _heightInput

    private val _weightInput = MutableStateFlow("")
    val weightInput: StateFlow<String> = _weightInput

    private val _bmi = MutableStateFlow("Enter valid height & weight")
    val bmi: StateFlow<String> = _bmi

    fun setHeight(value: String) {
        _heightInput.value = value.replace(',', '.')
        calculateBmi()
    }
    fun setWeight(value: String) {
        _weightInput.value = value.replace(',', '.')
        calculateBmi()
    }
    private fun calculateBmi() {
        val height = _heightInput.value.toFloatOrNull()
        val weight = _weightInput.value.toFloatOrNull()

        _bmi.value = if (height != null && weight != null && height > 0) {
            "Your BMI is %.2f".format(weight / (height * height))
        } else {
            "Enter valid height & weight"
        }
    }
}
