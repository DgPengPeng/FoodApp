package com.dong.foodsect.carousel;

import java.util.List;

/**
 * Created by dllo on 16/11/25.
 */
public class CarouselBean {


    /**
     * code : 200
     * data : {"banners":[{"ad_monitors":[],"channel":"all","id":792,"image_url":"http://img02.liwushuo.com/image/161124/g2693c0ds.jpg-w720","order":828,"status":0,"target":{"banner_image_url":"http://img02.liwushuo.com/image/161122/tbrig8f88.jpg-w300","banner_webp_url":"http://img02.liwushuo.com/image/161122/tbrig8f88.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img01.liwushuo.com/image/161122/2zr3jhj59.jpg-w720","cover_webp_url":"http://img01.liwushuo.com/image/161122/2zr3jhj59.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1479807085,"id":394,"posts_count":12,"status":1,"subtitle":"英亚美亚来袭，亚马逊海外购prime会员无限次包邮！","title":"亚马逊海外购","updated_at":1479969771},"target_id":394,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=394","type":"collection","webp_url":"http://img02.liwushuo.com/image/161124/g2693c0ds.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":791,"image_url":"http://img02.liwushuo.com/image/161121/605lxnijj.jpg-w720","order":827,"status":0,"target":{"banner_image_url":"http://img02.liwushuo.com/image/161121/tbdi0o0jy.jpg-w300","banner_webp_url":"http://img02.liwushuo.com/image/161121/tbdi0o0jy.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img02.liwushuo.com/image/161121/c7t1ca0ok.jpg-w720","cover_webp_url":"http://img02.liwushuo.com/image/161121/c7t1ca0ok.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1479722715,"id":393,"posts_count":6,"status":1,"subtitle":"那句说不出口的谢谢，小礼君帮你说","title":"感恩节礼物场","updated_at":1479722844},"target_id":393,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=393","type":"collection","webp_url":"http://img02.liwushuo.com/image/161121/605lxnijj.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":793,"image_url":"http://img01.liwushuo.com/image/161123/bf7tp15l8.jpg-w720","order":822,"status":0,"target_id":null,"target_type":"url","target_url":"liwushuo:///page?url=https%3A%2F%2Fevent.liwushuo.com%2Fadmin%2Fevent%2F36&page_action=navigation&login=false&type=url","type":"url","webp_url":"http://img01.liwushuo.com/image/161123/bf7tp15l8.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":784,"image_url":"http://img01.liwushuo.com/image/161117/ozy6i0638.jpg-w720","order":710,"status":0,"target":{"banner_image_url":"http://img03.liwushuo.com/image/161116/j6oak8l7e.jpg-w300","banner_webp_url":"http://img03.liwushuo.com/image/161116/j6oak8l7e.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img03.liwushuo.com/image/161116/4k87h4trw.jpg-w720","cover_webp_url":"http://img03.liwushuo.com/image/161116/4k87h4trw.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1479291563,"id":391,"posts_count":6,"status":1,"subtitle":"温暖守护你的少女心","title":"秋冬配饰","updated_at":1479291689},"target_id":391,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=391","type":"collection","webp_url":"http://img01.liwushuo.com/image/161117/ozy6i0638.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":785,"image_url":"http://img02.liwushuo.com/image/161117/2wi1llltc.jpg-w720","order":600,"status":0,"target":{"banner_image_url":"http://img01.liwushuo.com/image/161116/ab1it27xs.jpg-w300","banner_webp_url":"http://img01.liwushuo.com/image/161116/ab1it27xs.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img01.liwushuo.com/image/161116/wy73wk68f.jpg-w720","cover_webp_url":"http://img01.liwushuo.com/image/161116/wy73wk68f.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1479291363,"id":390,"posts_count":7,"status":1,"subtitle":"论吃货的自我修养","title":"零食研究所","updated_at":1479291474},"target_id":390,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=390","type":"collection","webp_url":"http://img02.liwushuo.com/image/161117/2wi1llltc.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":730,"image_url":"http://img02.liwushuo.com/image/161114/74ozwewrz.jpg-w720","order":500,"status":0,"target_id":null,"target_url":"liwushuo:///page?type=dailylucky","type":"url","webp_url":"http://img02.liwushuo.com/image/161114/74ozwewrz.jpg?imageView2/2/w/720/q/85/format/webp"}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private List<BannersBean> banners;

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public static class BannersBean {
            /**
             * ad_monitors : []
             * channel : all
             * id : 792
             * image_url : http://img02.liwushuo.com/image/161124/g2693c0ds.jpg-w720
             * order : 828
             * status : 0
             * target : {"banner_image_url":"http://img02.liwushuo.com/image/161122/tbrig8f88.jpg-w300","banner_webp_url":"http://img02.liwushuo.com/image/161122/tbrig8f88.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img01.liwushuo.com/image/161122/2zr3jhj59.jpg-w720","cover_webp_url":"http://img01.liwushuo.com/image/161122/2zr3jhj59.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1479807085,"id":394,"posts_count":12,"status":1,"subtitle":"英亚美亚来袭，亚马逊海外购prime会员无限次包邮！","title":"亚马逊海外购","updated_at":1479969771}
             * target_id : 394
             * target_type : url
             * target_url : liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=394
             * type : collection
             * webp_url : http://img02.liwushuo.com/image/161124/g2693c0ds.jpg?imageView2/2/w/720/q/85/format/webp
             */

            private String channel;
            private int id;
            private String image_url;
            private int order;
            private int status;
            private TargetBean target;
            private int target_id;
            private String target_type;
            private String target_url;
            private String type;
            private String webp_url;
            private List<?> ad_monitors;

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public TargetBean getTarget() {
                return target;
            }

            public void setTarget(TargetBean target) {
                this.target = target;
            }

            public int getTarget_id() {
                return target_id;
            }

            public void setTarget_id(int target_id) {
                this.target_id = target_id;
            }

            public String getTarget_type() {
                return target_type;
            }

            public void setTarget_type(String target_type) {
                this.target_type = target_type;
            }

            public String getTarget_url() {
                return target_url;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getWebp_url() {
                return webp_url;
            }

            public void setWebp_url(String webp_url) {
                this.webp_url = webp_url;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }

            public void setAd_monitors(List<?> ad_monitors) {
                this.ad_monitors = ad_monitors;
            }

            public static class TargetBean {
                /**
                 * banner_image_url : http://img02.liwushuo.com/image/161122/tbrig8f88.jpg-w300
                 * banner_webp_url : http://img02.liwushuo.com/image/161122/tbrig8f88.jpg?imageView2/2/w/300/q/85/format/webp
                 * cover_image_url : http://img01.liwushuo.com/image/161122/2zr3jhj59.jpg-w720
                 * cover_webp_url : http://img01.liwushuo.com/image/161122/2zr3jhj59.jpg?imageView2/2/w/720/q/85/format/webp
                 * created_at : 1479807085
                 * id : 394
                 * posts_count : 12
                 * status : 1
                 * subtitle : 英亚美亚来袭，亚马逊海外购prime会员无限次包邮！
                 * title : 亚马逊海外购
                 * updated_at : 1479969771
                 */

                private String banner_image_url;
                private String banner_webp_url;
                private String cover_image_url;
                private String cover_webp_url;
                private int created_at;
                private int id;
                private int posts_count;
                private int status;
                private String subtitle;
                private String title;
                private int updated_at;

                public String getBanner_image_url() {
                    return banner_image_url;
                }

                public void setBanner_image_url(String banner_image_url) {
                    this.banner_image_url = banner_image_url;
                }

                public String getBanner_webp_url() {
                    return banner_webp_url;
                }

                public void setBanner_webp_url(String banner_webp_url) {
                    this.banner_webp_url = banner_webp_url;
                }

                public String getCover_image_url() {
                    return cover_image_url;
                }

                public void setCover_image_url(String cover_image_url) {
                    this.cover_image_url = cover_image_url;
                }

                public String getCover_webp_url() {
                    return cover_webp_url;
                }

                public void setCover_webp_url(String cover_webp_url) {
                    this.cover_webp_url = cover_webp_url;
                }

                public int getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(int created_at) {
                    this.created_at = created_at;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getPosts_count() {
                    return posts_count;
                }

                public void setPosts_count(int posts_count) {
                    this.posts_count = posts_count;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getSubtitle() {
                    return subtitle;
                }

                public void setSubtitle(String subtitle) {
                    this.subtitle = subtitle;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(int updated_at) {
                    this.updated_at = updated_at;
                }
            }
        }
    }
}
