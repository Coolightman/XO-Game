package com.coolightman.model;

public enum Figure{
    X {
        public String toString() {
            return "X";
        }
    },

    O {
        public String toString() {
            return "O";
        }
    },

    EMPTY {
        public String toString() {
            return "_";
        }
    }
}
