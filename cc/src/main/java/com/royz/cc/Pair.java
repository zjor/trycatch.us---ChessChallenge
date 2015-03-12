package com.royz.cc;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<A, B> {
    private A a;
    private B b;

    public static <A, B> Pair of(A a, B b) {
        return new Pair(a, b);
    }

}
