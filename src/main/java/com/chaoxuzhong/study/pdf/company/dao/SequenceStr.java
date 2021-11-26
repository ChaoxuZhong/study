package com.chaoxuzhong.study.pdf.company.dao;

import lombok.Getter;

@Getter
public class SequenceStr {
    String sequence;

    private SequenceStr(String sequence) {
        this.sequence = sequence;
    }

    /**
     * 只能通过此方法构造，序列号从00开始
     * @return
     */
    public static SequenceStr init() {
        return new SequenceStr("00");
    }

    /**
     * 自增，01，02，03 到 99
     */
    public synchronized SequenceStr add() {
        int i = Integer.valueOf(sequence);
        i++;
        if (i > 99) {
            throw new UnsupportedOperationException("when i = 99, can not add any more");
        }
        String added = String.valueOf(i);
        if (added.length() == 1) {
            this.sequence = "0".concat(added);
            return this;
        }
        this.sequence = added;
        return this;
    }

}

