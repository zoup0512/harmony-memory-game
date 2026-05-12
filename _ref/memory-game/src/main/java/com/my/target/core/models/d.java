package com.my.target.core.models;

import java.util.ArrayList;

/* compiled from: AdService */
public final class d extends b {
    private String a;
    private int b = -1;
    private boolean c;
    private boolean d;
    private ArrayList<d> e = new ArrayList();
    private int f = -1;
    private d g;
    private String h;
    private boolean i;
    private ArrayList<i> j = new ArrayList();
    private ArrayList<i> k = new ArrayList();
    private ArrayList<i> l = new ArrayList();
    private int m;
    private String n;

    public final String a() {
        return this.a;
    }

    public final ArrayList<d> b() {
        return this.e;
    }

    public final boolean c() {
        return this.c;
    }

    public final void a(boolean z) {
        this.c = z;
    }

    public final boolean d() {
        return this.d;
    }

    public final void b(boolean z) {
        this.d = z;
    }

    public d(String str) {
        this.a = str;
    }

    public final int e() {
        return this.b;
    }

    public final void a(int i) {
        this.b = i;
    }

    public final ArrayList<i> f() {
        return this.j;
    }

    public final ArrayList<i> g() {
        return this.k;
    }

    public final ArrayList<i> h() {
        return this.l;
    }

    public final int i() {
        return this.m;
    }

    public final void b(int i) {
        this.m = i;
    }

    public final d j() {
        return this.g;
    }

    public final void a(d dVar) {
        this.g = dVar;
    }

    public final int k() {
        return this.f;
    }

    public final void c(int i) {
        this.f = i;
    }

    public final String l() {
        return this.h;
    }

    public final void a(String str) {
        this.h = str;
    }

    public final void b(d dVar) {
        this.e.add(dVar);
    }

    public final void a(i iVar) {
        this.j.add(iVar);
    }

    public final void b(i iVar) {
        this.k.add(iVar);
    }

    public final void c(i iVar) {
        this.l.add(iVar);
    }

    public final String m() {
        return this.n;
    }

    public final void b(String str) {
        this.n = str;
    }

    public final boolean n() {
        return this.i;
    }

    public final void o() {
        this.i = true;
    }
}
