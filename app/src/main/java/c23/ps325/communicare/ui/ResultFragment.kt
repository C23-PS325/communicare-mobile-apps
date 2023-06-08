package c23.ps325.communicare.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import c23.ps325.communicare.databinding.FragmentResultBinding
import c23.ps325.communicare.response.FramesPrediction
import c23.ps325.communicare.response.Prediction
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class ResultFragment : Fragment() {
    private lateinit var binding : FragmentResultBinding
    private var topFaceExp = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataResult = Prediction(FramesPrediction(angry = 59.34, sad = 24.18, surprise = 12.09, happy = 2.2, fear = 2.2), "Angry")
        setupChart(dataResult)
        setupRecommendation(dataResult)
    }

    private fun setupChart(dataPrediction : Prediction){
        val listPieEntry = ArrayList<PieEntry>()
        var maxExp = 0.0
        for (expression in dataPrediction.frames){
            listPieEntry.add(PieEntry(expression.second.toFloat(), expression.first))
            if (expression.second > maxExp){
                maxExp = expression.second
                topFaceExp = expression.first
            }
        }
        val set = PieDataSet(listPieEntry, "Face Expression Prediction")
        set.colors = ColorTemplate.COLORFUL_COLORS.toList()
        set.valueTextSize = 14f
        set.valueTextColor = resources.getColor(android.R.color.white)
        set.valueLinePart1Length = 25f
        val data = PieData(set)
        binding.chartFace.data = data
        binding.chartFace.minAngleForSlices = 30f
        binding.chartFace.invalidate()
    }

    private fun setupRecommendation(dataPrediction : Prediction){
        val faceRecommendation = when(topFaceExp){
//            "Angry" -> faceRecommendation = "You look angry, try to calm down and take a deep breath"
//            "Sad" -> faceRecommendation = "You look sad, try to smile and think of something that makes you happy"
//            "Surprise" -> faceRecommendation = "You look surprised, try to calm down and take a deep breath"
//            "Happy" -> faceRecommendation = "You look happy, keep it up!"
//            "Fear" -> faceRecommendation = "You look afraid, try to calm down and take a deep breath"
            "Angry" -> "Avoid displaying anger during interviews or communication as it can be perceived as unprofessional and off-putting. Instead, maintain a calm and composed demeanor, even if you encounter challenging or frustrating situations."
            "Sad" -> "While it's important to be genuine and authentic, excessive displays of sadness may not be suitable during interviews or communication, as it can convey a lack of enthusiasm or confidence. Instead, strive for a neutral or slightly positive expression to maintain a professional demeanor."
            "Surprise" -> "Mild surprise expressions can be acceptable during interviews or communication, as long as they are genuine and not exaggerated. However, it's crucial to quickly transition into a composed and attentive expression, showing that you are engaged and ready to respond."
            "Happy" -> "Demonstrating a warm and genuine smile can be highly beneficial during interviews or communication. A genuine smile helps create a positive and welcoming atmosphere, showing enthusiasm and a pleasant demeanor."
            "Fear" -> "It's important to minimize signs of fear or anxiety during interviews or communication, as they can project insecurity or lack of confidence. Instead, focus on maintaining a composed expression, displaying confidence and attentiveness."
            else -> {
                "You look happy, keep it up!"
            }
        }

        val soundRecommendation = when(dataPrediction.audio){
            "Angry" -> "Maintain a calm and controlled tone of voice. Avoid raising your voice or sounding aggressive, as it can come across as confrontational. Instead, focus on expressing your thoughts assertively and confidently while keeping your emotions in check."
            "Sad" -> "While it's important to be genuine and authentic, excessive sadness in your voice can be perceived as a lack of enthusiasm or confidence. Strive for a neutral or slightly positive tone, ensuring that your voice conveys engagement and interest in the conversation."
            "Surprise" -> "Use a tone of voice that reflects genuine surprise and interest. Vary your pitch slightly to convey the element of surprise effectively. Ensure that your surprise doesn't overshadow your ability to respond or engage actively in the conversation."
            "Happy" -> "Infuse your voice with warmth and positivity. Use a slightly elevated tone with a smile in your voice to convey enthusiasm and engagement. However, be mindful of striking a balance, ensuring that your happiness doesn't come across as overly exaggerated or forced."
            "Fear" -> "Minimize signs of fear or anxiety in your voice, as they can convey insecurity or lack of confidence. Focus on maintaining a steady and composed tone, speaking clearly and articulately. Project confidence and control in your voice to inspire trust and credibility."
            else -> {
                "You sound happy, keep it up!"
            }
        }

        binding.txtFaceRecommendation.text = faceRecommendation
        binding.txtSoundResult.text = "Sound Emotion Result = ${dataPrediction.audio}"
        binding.txtSoundRecommendation.text = soundRecommendation
    }

}