package com.learning.ruslan.databases;

import android.database.Cursor;
import android.database.CursorWrapper;

import androidx.annotation.Nullable;

import com.learning.ruslan.Assent;
import com.learning.ruslan.Paronym;
import com.learning.ruslan.Suffix;
import com.learning.ruslan.Task;
import com.learning.ruslan.Word;

import org.jetbrains.annotations.NotNull;

public class TaskCursorWrapper extends CursorWrapper {

    public TaskCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Assent getAssent() {
        return new Assent(
                getString(getColumnIndex(RusDbSchema.AssentTable.Cols.WORD)),
                getInt(getColumnIndex(RusDbSchema.AssentTable.Cols.POSITION)),
                getInt(getColumnIndex(RusDbSchema.AssentTable.Cols.CHECKED)) == 1,
                getInt(getColumnIndex(RusDbSchema.AssentTable.Cols.ID)));
    }

    public Suffix getSuffix() {
        return new Suffix(
                getString(getColumnIndex(RusDbSchema.SuffixTable.Cols.WORD)),
                getInt(getColumnIndex(RusDbSchema.SuffixTable.Cols.POSITION)),
                getString(getColumnIndex(RusDbSchema.SuffixTable.Cols.ALTERNATIVE)));
    }

    public Paronym getParonym() {
        return new Paronym(
                getString(getColumnIndex(RusDbSchema.ParonymTable.Cols.WORD)),
                getString(getColumnIndex(RusDbSchema.ParonymTable.Cols.VARIANTS)), getString(getColumnIndex(RusDbSchema.ParonymTable.Cols.ALTERNATIVE))
        );
    }

    @NotNull
    public Task getTask(int typeId) {
        switch (typeId) {
            case Word.SuffixId:
                return getSuffix();
            case Word.ParonymId:
                return getParonym();
            default:
                return getAssent();
        }
    }
}
