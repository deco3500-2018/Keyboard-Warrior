package com.kw.kw.domain;

import java.util.Date;
import java.util.List;
//manual parse json
public class NewsCenter2 {
    private List<DetailPagerData> articles;
    private int totalResults;
    private String status;

    public List<DetailPagerData> getArticles() {
        return articles;
    }

    public void setArticles(List<DetailPagerData> articles) {
        this.articles = articles;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NewsCenter2{" +
                "articles=" + articles +
                ", totalResults=" + totalResults +
                ", status='" + status + '\'' +
                '}';
    }

    public  static class DetailPagerData{
        private String author;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private Date publishedAt;
        private String content;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        public Date getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(Date publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<ChildrenData> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenData> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "DetailPagerData{" +
                    "author='" + author + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", url='" + url + '\'' +
                    ", urlToImage='" + urlToImage + '\'' +
                    ", publishedAt=" + publishedAt +
                    ", content='" + content + '\'' +
                    ", children=" + children +
                    '}';
        }

        private List<ChildrenData> children;

        public static class ChildrenData{
            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            @Override
            public String toString() {
                return "ChildrenData{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }
        }
    }

}
