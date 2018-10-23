package com.entity;

public class Dept {
    private Long deptno;
    private String dname;
    private String loc;

    public void setDeptno(Byte deptno) {
        this.deptno = deptno;
    }

    public Long getDeptno() {
        return deptno;
    }

    public void setDeptno(Long deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dept dept = (Dept) o;

        if (deptno != null ? !deptno.equals(dept.deptno) : dept.deptno != null) return false;
        if (dname != null ? !dname.equals(dept.dname) : dept.dname != null) return false;
        if (loc != null ? !loc.equals(dept.loc) : dept.loc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = deptno != null ? deptno.hashCode() : 0;
        result = 31 * result + (dname != null ? dname.hashCode() : 0);
        result = 31 * result + (loc != null ? loc.hashCode() : 0);
        return result;
    }
}
