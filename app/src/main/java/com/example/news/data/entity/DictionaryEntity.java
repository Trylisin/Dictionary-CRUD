package com.example.news.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dictionary")
public class DictionaryEntity implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String word;
    public String partofspeech;
    public String meaning;

    @ColumnInfo(name = "date_created")
    public String datecreated;


    public DictionaryEntity(Parcel in) {
        id = in.readInt();
        word = in.readString();
        partofspeech = in.readString();
        meaning = in.readString();
        datecreated = in.readString();
    }

    public DictionaryEntity() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPartofspeech() {
        return partofspeech;
    }

    public void setPartofspeech(String partofspeech) {
        this.partofspeech = partofspeech;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public static Creator<DictionaryEntity> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(word);
        dest.writeString(partofspeech);
        dest.writeString(meaning);
        dest.writeString(datecreated);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DictionaryEntity> CREATOR = new Creator<DictionaryEntity>() {
        @Override
        public DictionaryEntity createFromParcel(Parcel in) {
            return new DictionaryEntity(in);
        }

        @Override
        public DictionaryEntity[] newArray(int size) {
            return new DictionaryEntity[size];
        }
    };
    @Override
    public String toString() {
        return "DictionaryEntity{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", partofspeech='" + partofspeech + '\'' +
                ", meaning='" + meaning + '\'' +
                ", datecreated='" + datecreated + '\'' +
                '}';
    }

}

