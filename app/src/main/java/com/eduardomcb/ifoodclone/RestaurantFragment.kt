package com.eduardomcb.ifoodclone

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.eduardomcb.ifoodclone.adapter.BannerAdapter
import com.eduardomcb.ifoodclone.adapter.CatagoryAdapter
import com.eduardomcb.ifoodclone.adapter.MoreShopAdapter
import com.eduardomcb.ifoodclone.adapter.ShopAdapter
import com.eduardomcb.ifoodclone.databinding.FragmentRestaurantBinding
import com.eduardomcb.ifoodclone.model.Banner
import com.eduardomcb.ifoodclone.model.Category
import com.eduardomcb.ifoodclone.model.MoreShop
import com.eduardomcb.ifoodclone.model.Shop

class RestaurantFragment : Fragment(R.layout.fragment_restaurant) {

    private var binding: FragmentRestaurantBinding? = null

    private var filters = arrayOf(
        FilterItem(1, "Ordenar", closeIcon = R.drawable.baseline_keyboard_arrow_down_24),
        FilterItem(2, "Pra retirar", icon = R.drawable.baseline_directions_walk_24),
        FilterItem(3, "Entrega grátis"),
        FilterItem(4, "Vale-refeição", closeIcon = R.drawable.baseline_keyboard_arrow_down_24),
        FilterItem(5, "Distância", closeIcon = R.drawable.baseline_keyboard_arrow_down_24),
        FilterItem(6, "Entrega Parceria"),
        FilterItem(7, "Super Restaurante"),
        FilterItem(8, "Filtros", closeIcon = R.drawable.baseline_filter_list_24),
    )

    private lateinit var catagoryAdapter: CatagoryAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var shopAdapter: ShopAdapter
    private  lateinit var moreShopAdapter: MoreShopAdapter
    private val categoryList: MutableList<Category> = mutableListOf()
    private val bannerList: MutableList<Banner> = mutableListOf()

    private val snapHelper = LinearSnapHelper()
    private var position: Int? = RecyclerView.NO_POSITION

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRestaurantBinding.bind(view)

        binding?.let {
            it.rvCategory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvCategory.setHasFixedSize(true)
            catagoryAdapter = CatagoryAdapter(requireContext(), loadCategoryItens())
            it.rvCategory.adapter = catagoryAdapter



            it.rvBanners.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvBanners.setHasFixedSize(true)
            bannerAdapter = BannerAdapter(requireContext(), loadBanners())
            it.rvBanners.adapter = bannerAdapter
            it.rvBanners.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        notifyPositionChanged(recyclerView)
                    }
                }
            })

            it.rvShops.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.rvShops.setHasFixedSize(true)
            shopAdapter = ShopAdapter(requireContext(), loadShops())
            it.rvShops.adapter = shopAdapter

            addDots(it.dots, loadBanners().size, 0)

            it.rvMoreShops.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            it.rvMoreShops.setHasFixedSize(true)
            moreShopAdapter = MoreShopAdapter(requireContext(), loadMoreShops())
            it.rvMoreShops.adapter = moreShopAdapter

            filters.forEach { filter ->
                it.chipGroupFilter.addView(filter.toChip(requireContext()))
            }
        }
    }

    private fun addDots(container: LinearLayout, size: Int, position: Int) {
        container.removeAllViews()

        Array(size) {
            val textView = TextView(context).apply {
                text = getString(R.string.dotted)
                textSize = 20f
                setTextColor(
                    if (position == it) ContextCompat.getColor(
                        context,
                        android.R.color.black
                    ) else ContextCompat.getColor(context, android.R.color.darker_gray)
                )
            }
            container.addView(textView)
        }
    }

    private fun notifyPositionChanged(rv: RecyclerView) {
        val layoutManager = rv.layoutManager
        val view = snapHelper.findSnapView(layoutManager)
        val position =
            if (view == null) RecyclerView.NO_POSITION else layoutManager?.getPosition(view)

        val positionChanged = this.position != position
        if (positionChanged) {
            addDots(binding!!.dots, bannerAdapter.itemCount, position ?: 0)
        }
        this.position = position
    }

    private fun loadCategoryItens() = mutableListOf(
        Category(
            1,
            "https://static.ifood-static.com.br/image/upload/f_auto/webapp/landingV2/market.png",
            "Marcado",
            0xFFB6D048
        ),
        Category(
            2,
            "https://static.ifood-static.com.br/image/upload/f_auto/webapp/landingV2/restaurant.png",
            "Restaurante",
            0xFFE91D2D
        ),
        Category(
            3,
            "https://i.ibb.co/TMCYcvy/Farmacia-NQvy-fotor-bg-remover-20230519225145.png",
            "Farmácia",
            0xFFFF0000
        ),
        Category(
            4,
            "https://static.ifood-static.com.br/image/upload/f_auto/webapp/landingV2/drinks.png",
            "Bebidas",
            0xFFF6D553
        ),
        Category(
            5,
            "https://i.ibb.co/5YDyYxY/Gourmet-k41-F-fotor-bg-remover-2023051922542.png",
            "Gourmet",
            0xFFE91D2D
        ),
        Category(
            6,
            "https://i.ibb.co/1mG7s53/Pet-AGON-fotor-bg-remover-20230519225238.png",
            "Pet",
            0xFF8C60C5
        )
    )


    private fun loadBanners() = mutableListOf(
        Banner(
            1,
            "https://static.ifood-static.com.br/image/upload/t_high/discoveries/07AdsGroceriesMelhoresOfertasPrincipal04_Lca4.png?imwidth=1920"
        ),
        Banner(
            2,
            "https://static.ifood-static.com.br/image/upload/t_high/discoveries/1904levou12pagou11heinekenv3principal_xA9E.png?imwidth=1920"
        ),
        Banner(
            3,
            "https://static.ifood-static.com.br/image/upload/t_high/discoveries/0305farmacinha30principal_YWVE.png?imwidth=1920"
        )
    )

    private fun loadShops() = mutableListOf(
        Shop(
            1,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/c1ba5021-e195-4439-828e-4340e8435ce7/202207281616_HtGR_i.jpg?imwidth=256",
            "Minas Açaí"
        ),
        Shop(
            2,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/de526231-3963-4977-aeac-60685c341f73/202305201338_oWn1_i.jpg?imwidth=256",
            "Bolo Fantástico"
        ),
        Shop(
            3,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/c5bfbab4-77d3-48ca-a16f-3e2ce3e92e7e/202305020056_Nity_i.jpg?imwidth=256",
            "Gênesis Lanchonete"
        ),
        Shop(
            4,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/4adf77dd-f8f9-4399-aa32-011513593a13/202305162124_DJIZ_i.jpg?imwidth=256",
            "Ss Comedoria"
        ),
        Shop(
            5,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/1e613e07-4730-41f3-baae-04638df1f2b5/202301311423_wGsn_i.jpg?imwidth=256",
            "Açaí Online"
        ),
        Shop(
            6,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/4929a30f-15e8-4142-a787-ecedaadbc82e/202207281504_DIHw_i.jpg?imwidth=256",
            "Açaí e Cia"
        ),
        Shop(
            7,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/e96188ac-394f-4b91-b4b1-28ce88e721fb/202010011830_YR8d_i.jpg?imwidth=256",
            "Kitoop Lanchonete Gruta de Lourdes"
        )
    )

    private fun loadMoreShops() = mutableListOf(
        MoreShop(
            1,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/25afbea1-b8cd-4cdc-8ff0-9782f627325a/201906161759_QuuE_i.jpg?imwidth=256",
            "Sanduiche Sobrenatural",
            4.4,
            "Variada",
            8.9,
            "38-48",
            22.99
        ),
        MoreShop(
            2,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/b5619052-1794-4240-9f81-4d7b38005a8c/202302132007_U1w0_i.jpg?imwidth=256",
            "Mila Doceria Mcz",
            4.7,
            "Doces & Bolos",
            8.0,
            "50-60",
            19.98
        ),
        MoreShop(
            3,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/0160ebea-9b11-4687-8eab-ffa9796dc11b/202201071737_b5t5_i.png?imwidth=256",
            "Mr. King Foods",
            4.6,
            "Lanches",
            3.0,
            "59-69",
            0.0
        ),
        MoreShop(
            4,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/0f37fc92-54ca-4696-852d-e3ceda217be4/202002241534_LUzf_.jpeg?imwidth=256",
            "Le Croissant",
            4.2,
            "Doces & Bolos",
            9.2,
            "75-85",
            24.99
        ),
        MoreShop(
            5,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/1f18ba99-78a1-4438-bd2c-fd4be0f7661d/201904290750_IYAc_d.jpg?imwidth=256",
            "Churrascaria Acauã",
            4.5,
            "Carnes",
            5.5,
            "80-90",
            9.0
        ),
        MoreShop(
            6,
            "https://static.ifood-static.com.br/image/upload/t_thumbnail/logosgde/23e57841-a7b0-4baf-9e67-3e912d79822a/201903271204_tJjm_i.jpg",
            "Churrasco do Xosqui",
            4.7,
            "Carnes",
            8.9,
            "63-73",
            24.99
        ),
        MoreShop(
            7,
            "https://static.ifood-static.com.br/image/upload/t_medium/logosgde/fcde4145-3755-43f4-92c4-f258073a07c3/202303111249_rzKE_i.jpg?imwidth=256",
            "Mundo da Pizza",
            4.1,
            "Pizza",
            1.6,
            "50-60",
            22.99
        )
    )
}