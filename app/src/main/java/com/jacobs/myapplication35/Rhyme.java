package com.jacobs.myapplication35;

public class Rhyme {

    private int _id;
    private String _name;
    private String _two_last;
    private String _three_last;

    public Rhyme(){
    }

    public Rhyme(int _id, String _name, String _two_last, String _three_last) {
        this._id = _id;
        this._name = _name;
        this._two_last = _two_last;
        this._three_last = _three_last;
    }

    public Rhyme(String name) {
        this._name = name;
    }

    public int get_id() {
        return _id;
    }

    public String get_name() {
        return _name;
    }

    public String get_two_last() {
        return _two_last;
    }

    public String get_three_last() {
        return _three_last;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_two_last(String _two_last) {
        this._two_last = _two_last;
    }

    public void set_three_last(String _three_last) {
        this._three_last = _three_last;
    }

    @Override
    public String toString() {
        return "Rhyme{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _two_last='" + _two_last + '\'' +
                ", _three_last='" + _three_last + '\'' +
                '}';
    }
}
