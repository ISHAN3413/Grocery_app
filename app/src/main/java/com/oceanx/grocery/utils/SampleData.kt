package com.oceanx.grocery.utils

import com.oceanx.grocery.data.model.Product

object SampleData {
    fun getProducts() = listOf(
        Product(1,  "Amul Milk 1L",         68.0,  "Dairy",         "🥛"),
        Product(2,  "Brown Bread",           45.0,  "Bakery",        "🍞"),
        Product(3,  "Basmati Rice 1kg",      120.0, "Grains",        "🍚"),
        Product(4,  "Tata Salt",             28.0,  "Essentials",    "🧂"),
        Product(5,  "Maggi Noodles",         14.0,  "Snacks",        "🍜"),
        Product(6,  "Cadbury Dairy Milk",    40.0,  "Snacks",        "🍫"),
        Product(7,  "Tropicana Orange 1L",   135.0, "Beverages",     "🍊"),
        Product(8,  "Dettol Soap",           55.0,  "Personal Care", "🧼"),
        Product(9,  "Aashirvaad Atta 5kg",   275.0, "Grains",        "🌾"),
        Product(10, "Colgate Toothpaste",    89.0,  "Personal Care", "🪥"),
        Product(11, "Lays Classic Chips",    20.0,  "Snacks",        "🍿"),
        Product(12, "Eggs (12 pcs)",         89.0,  "Dairy",         "🥚"),
        Product(13, "Bananas (6 pcs)",       40.0,  "Fruits",        "🍌"),
        Product(14, "Tomatoes 500g",         30.0,  "Vegetables",    "🍅"),
        Product(15, "Onions 1kg",            35.0,  "Vegetables",    "🧅"),
        Product(16, "Coca Cola 2L",          95.0,  "Beverages",     "🥤"),
        Product(17, "Butter 100g",           55.0,  "Dairy",         "🧈"),
        Product(18, "Green Tea 25 bags",     120.0, "Beverages",     "🍵"),
        Product(19, "Surf Excel 1kg",        180.0, "Household",     "🫧"),
        Product(20, "Potatoes 1kg",          30.0,  "Vegetables",    "🥔"),
    )
}