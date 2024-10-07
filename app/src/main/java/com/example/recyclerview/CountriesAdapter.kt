package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class CountriesAdapter(
    private var countryNameList : ArrayList<String>,
    private var countryDetailsList : ArrayList<String>,
    private var countryImageViewList : ArrayList<Int>,
    private var context: Context
) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>(){

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var textViewCountryName : TextView = itemView.findViewById(R.id.textViewCountryName)
        var textViewDetails : TextView = itemView.findViewById(R.id.textViewDetails)
        var imageView : CircleImageView = itemView.findViewById(R.id.imageView)
        var cardView : CardView = itemView.findViewById(R.id.cardView)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
       val view : View = LayoutInflater.from(parent.context).inflate(R.layout.card_design,parent,false)

        return CountryViewHolder(view)
    }



    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {

        holder.textViewCountryName.text = countryNameList[position]
        holder.textViewDetails.text = countryDetailsList[position]
        holder.imageView.setImageResource(countryImageViewList[position])
        holder.cardView.setOnClickListener{
            Toast.makeText(context,"You selected ${countryNameList[position]}",Toast.LENGTH_LONG).show()
        }

    }

    override fun getItemCount(): Int {
            return countryNameList.size
    }

}