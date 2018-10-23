package com.entity;

import java.sql.Date;
import java.sql.Time;

public class Emp {
    private Long empno;
    private String ename;
    private String job;
    private Long mgr;
    private Time hiredate;
    private Long sal;
    private Long comm;
    private Integer mrg;
    private Byte deptno;

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public void setComm(Double comm) {
        this.comm = comm;
    }

    public Long getEmpno() { return empno;
    }

    public void setEmpno(Long empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getMgr() {
        return mgr;
    }

    public void setMgr(Long mgr) {
        this.mgr = mgr;
    }

    public Time getHiredate() {
        return hiredate;
    }

    public void setHiredate(Time hiredate) {
        this.hiredate = hiredate;
    }

    public Long getSal() {
        return sal;
    }

    public void setSal(Long sal) {
        this.sal = sal;
    }

    public Long getComm() {
        return comm;
    }

    public void setComm(Long comm) {
        this.comm = comm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Emp emp = (Emp) o;

        if (empno != null ? !empno.equals(emp.empno) : emp.empno != null) return false;
        if (ename != null ? !ename.equals(emp.ename) : emp.ename != null) return false;
        if (job != null ? !job.equals(emp.job) : emp.job != null) return false;
        if (mgr != null ? !mgr.equals(emp.mgr) : emp.mgr != null) return false;
        if (hiredate != null ? !hiredate.equals(emp.hiredate) : emp.hiredate != null) return false;
        if (sal != null ? !sal.equals(emp.sal) : emp.sal != null) return false;
        if (comm != null ? !comm.equals(emp.comm) : emp.comm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = empno != null ? empno.hashCode() : 0;
        result = 31 * result + (ename != null ? ename.hashCode() : 0);
        result = 31 * result + (job != null ? job.hashCode() : 0);
        result = 31 * result + (mgr != null ? mgr.hashCode() : 0);
        result = 31 * result + (hiredate != null ? hiredate.hashCode() : 0);
        result = 31 * result + (sal != null ? sal.hashCode() : 0);
        result = 31 * result + (comm != null ? comm.hashCode() : 0);
        return result;
    }

    public Integer getMrg() {
        return mrg;
    }

    public void setMrg(Integer mrg) {
        this.mrg = mrg;
    }

    public Byte getDeptno() {
        return deptno;
    }

    public void setDeptno(Byte deptno) {
        this.deptno = deptno;
    }
}
