package com.example.abnerrxretrofit.test;


import com.example.abnerrxretrofit.model.IModel;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2.
 * 测试bean，只取title
 */

public class TestBean implements IModel {
    private int count;
    private int total;
    private List<Subjects> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public static class Subjects{
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
