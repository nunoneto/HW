package pt.nunoneto.hw.ui

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import pt.nunoneto.hw.R
import pt.nunoneto.hw.entities.Property

class PropertyListAdapter(private val context: Context) : RecyclerView.Adapter<PropertyListAdapter.ViewHolder>() {

    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }

    var properties: List<Property> = ArrayList()

    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.property_list_item, container, false))
    }

    override fun getItemCount(): Int = properties.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val property = properties[position]

        // name
        viewHolder.name.text = property.name

        // featured
        viewHolder.featured.visibility = if (property.featured) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }

        // rating
        if (property.rating != null) {
            viewHolder.rating.visibility = View.VISIBLE
            viewHolder.rating.text = context.getString(R.string.rating, property.rating)
        } else {
            viewHolder.rating.visibility = View.INVISIBLE
        }

        // price per night
        if (property.lowestPricePerNight != null) {
            viewHolder.pricePerNight.visibility = View.VISIBLE

            val price = String.format("%.1f", property.lowestPricePerNight)
            val span = SpannableString(context.getString(R.string.price_per_night, price))

            span.setSpan(RelativeSizeSpan(1.5f), 0, price.length, 0)
            span.setSpan(StyleSpan(Typeface.BOLD), 0, price.length, 0)
            span.setSpan(StyleSpan(Typeface.ITALIC), price.length, price.length + 1, 0)

            viewHolder.pricePerNight.text = span

        } else {
            viewHolder.pricePerNight.visibility = View.INVISIBLE
        }

        // image
        if (property.imageUrl.isNullOrEmpty()) {
            viewHolder.image.visibility = View.INVISIBLE

        } else {
            viewHolder.image.visibility = View.VISIBLE

            Glide.with(context)
                    .load(property.imageUrl)
                    .apply(RequestOptions().placeholder(R.drawable.placeholder))
                    .apply(RequestOptions.circleCropTransform())
                    .into(viewHolder.image)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_property_name)
        val image: ImageView = itemView.findViewById(R.id.iv_property_image)
        val featured: TextView = itemView.findViewById(R.id.tv_property_featured)
        val rating: TextView = itemView.findViewById(R.id.tv_rating)
        val pricePerNight: TextView = itemView.findViewById(R.id.tv_price_per_night)
    }
}