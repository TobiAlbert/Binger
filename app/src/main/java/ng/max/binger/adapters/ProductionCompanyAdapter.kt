package ng.max.binger.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.DisplayCutout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_production_company.view.*
import ng.max.binger.R
import ng.max.binger.data.ProductionCompany
import ng.max.binger.utils.DisplayUtils

class ProductionCompanyAdapter: RecyclerView.Adapter<ProductionCompanyAdapter.ProductionViewHolder>() {

    var companyList = emptyList<ProductionCompany>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, resourceLayout: Int): ProductionViewHolder {
        mContext = parent.context
        val itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.list_item_production_company, parent, false)
        return ProductionViewHolder(itemView)
    }

    override fun getItemCount(): Int = companyList.size

    override fun onBindViewHolder(holder: ProductionViewHolder, position: Int) = holder.bind(companyList[position])


    inner class ProductionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(company: ProductionCompany) {
            itemView.companyOrigin.text = company.originCountry
            itemView.companyName.text = company.name
            Glide.with(mContext)
                    .load(DisplayUtils.getImageUrl(company.logoPath))
                    .into(itemView.companyLogo)
        }
    }
}