package com.dong.foodsect.tools;

/**
 * Created by dllo on 16/11/24.
 */
public class AllUrl {

    public static final String FOODENCYCLOPEDIA = "http://food.boohee.com/fb/v1/categories/list";

    // 首页
    public static final String HOME_PAGE = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10";
    // 测评
    public static final String EVALUATION = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=2&per=10";
    // 知识
    public static final String KNOWLEDGE = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=3&per=10";
    // 美食
    public static final String DELICACY = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=4&per=10";

    // 轮播图
    public static final String CAROUSE = "http://api.liwushuo.com/v2/banners";

    public static final String EVA_HEAD = "http://food.boohee.com/fb/v1/feeds/category_feed?page=";
    public static final String EVA_FOOTER = "&category=2&per=10";


    public static final String KNOW_FOOTER = "&category=3&per=10";
    public static final String DEL_FOOTER = "&category=4&per=10";
    public static final String HOME_PAGE_FOOTER = "&category=1&per=10";


    // 食物百科
    public static final String FOOD_ONE = "http://food.boohee.com/fb/v1/foods?kind=";
    public static final String FOOD_TWO = "&value=";
    public static final String FOOD_THREE = "&order_by=1&page=";
    public static final String FOOD_FOUR = "&order_asc=0";
    public static final String FOOD_FIVE = "&order_by=1&page=1";
    // RecyclerView 接口拼接
    public static final String FOOD_NUTRITION = "&order_by=";
    public static final String FOOD_NUTRITION_CETER = "&page=";
    public static final String FOOD_NUTRITION_TAIL = "&order_asc=0";
    // ListView 接口拼接
    public static final String FOOD_SUB_VALUE = "&sub_value=";
    public static final String FOOD_SUB_VALUE_TWO = "&order_by=1&page=";




    // 营养素
    public static final String NUTRIENT = "http://food.boohee.com/fb/v1/foods/sort_types";

    // 搜索详情页 Rv 接口
    public static final String SEARCH = "http://food.boohee.com/fb/v1/keywords?token=6CnGjxv1F6JsbAgBH8mD";

    // 搜索 点击跳转详情页 接口拼接
    public static final String SEARCH_HEAD = "http://food.boohee.com/fb/v1/search?page=";
    public static final String SEARCH_MID = "&order_asc=desc&q=";


    // http://food.boohee.com/fb/v1/foods?kind=brand&value=0(&sub_value=13)&order_by=1&page=1&order_asc=0

    // 下面的pop详情页网址
    //http://food.boohee.com/fb/v1/foods?kind=group&value=1&order_by=2&page=1&order_asc=0

   // http://food.boohee.com/fb/v1/foods?kind=group&value=1&order_by=1&page=2&order_asc=0

    //http://food.boohee.com/fb/v1/foods?kind=group&value=2&sub_value=22&order_by=1&page=1&order_asc=0
  //  http://food.boohee.com/fb/v1/foods?kind=group&value=2&sub_values=23&order_by=1&page=1&order_asc=0



}
