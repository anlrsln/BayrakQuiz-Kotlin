package com.example.bayrakquiz.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.bayrakquiz.Classes.Bayraklar
import com.example.bayrakquiz.Classes.BayraklarDao
import com.example.bayrakquiz.Classes.VeriTabaniYardimcisi
import com.example.bayrakquiz.R
import com.example.bayrakquiz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var sorular:ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler:ArrayList<Bayraklar>
    private lateinit var dogruSecenek:Bayraklar
    private lateinit var tumSecenekler:HashSet<Bayraklar>
    private lateinit var vt:VeriTabaniYardimcisi
    private lateinit var btnList:ArrayList<Button>



    private var soruSayac = 0
    private var dogruSayac = 0
    private var yanlisSayac = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // veritabanına erişim için vt objesi oluşturuldu
        vt = VeriTabaniYardimcisi(this)

        // activity ilk açıldığında veritabanından rastgele 5 bayrak listeye eklendi
        sorular = BayraklarDao().random5flag(vt)



        // Butonlar bir listeye aktarıldı ve tıklanıldığında çalışması için fonksiyon tanımlandı
        btnList = ArrayList<Button>()
        btnList.add(binding.buttonA)
        btnList.add(binding.buttonB)
        btnList.add(binding.buttonC)
        btnList.add(binding.buttonD)
        btnKontrol()

        // Sorunun yüklenmesi için fonksiyon çalıştı
        soruYukle()

    }


    fun soruYukle(){
        // Soru sayaç
        binding.textViewSoruSayi.text="${soruSayac+1}. Soru"


        // Dogru seçenek aktarıldı
        dogruSecenek = sorular.get(soruSayac)
        binding.imageView2.setImageResource(resources.getIdentifier(dogruSecenek.bayrak_resim,"drawable",packageName))
        binding.textViewDogru.text="Dogru : $dogruSayac"


        // Yanlis seçenekler aktarıldı
        yanlisSecenekler = BayraklarDao().random3WrongFlag(vt,dogruSecenek.bayrak_id)
        binding.textViewYanlis.text="Yanlis : $yanlisSayac"

        // Tum Secenekler aktarıldı, ekranda karışık gözükebilmesi için HashSet kullanıldı
        tumSecenekler = HashSet()
        for(s in yanlisSecenekler){
            tumSecenekler.add(s)
        }
        tumSecenekler.add(dogruSecenek)

        // Button yazıları aktarıldı
        var i = 0
        for (btn in btnList){
            btn.text=tumSecenekler.elementAt((i)).bayrak_ad
            i++
        }

    }





    fun soruSayacKontrol(){

        soruSayac++

        // Soru sayacı arttırıldı, 5 ten küçükse yeni soru oluşturuldu, değilse sonuç ekranına geçildi

        if(soruSayac!=5){
            soruYukle()
        }else{
            val intent = Intent(this@QuizActivity,ResultActivity::class.java)
            intent.putExtra("dogruSayac",dogruSayac)
            startActivity(intent)
            finish()
        }
    }


    // Butonlar listeye aktarıldı, liste döngüye alındı, eğer tıklanan butonun texti doğru seçenek nesnesinin adına eşitse
    // doğru butona basıldı, yeni soru yüklendi , doğru sayacı arttı, değilse yeni soru yüklendi ve yanlış sayacı arttı

    fun btnKontrol(){
        for(btn in btnList){
            btn.setOnClickListener {
                if(btn.text.toString()==dogruSecenek.bayrak_ad){
                    soruSayacKontrol()
                    dogruSayac++
                    binding.textViewDogru.text="Dogru : $dogruSayac"
                }else{
                    soruSayacKontrol()
                    yanlisSayac++
                    binding.textViewYanlis.text="Yanlis : $yanlisSayac"
                }
            }
        }
    }

}