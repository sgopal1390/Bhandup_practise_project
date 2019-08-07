package com.example.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "programmers")
public class Programmer {
	@Id
	private int pid;
	private String pname;
	private double salary;
	@ManyToMany(targetEntity = Project.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "programmers_projects", joinColumns = @JoinColumn(name = "programmer_id", referencedColumnName = "pid"), inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "proid"))
	@JsonBackReference
	private Set<Project> projects = new HashSet<Project>();

	public Programmer() {
		System.out.println("Programmer:0-param constructor");
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Set<Project> getProjects() {
		return projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	@Override
	public String toString() {
		return "Programmer [pid=" + pid + ", pname=" + pname + ", salary=" + salary + "]";
	}

}

/*
 * ==== table1===== create table programmers ( pid number(5) primary key, pname
 * varchar2(20), salary number(10) ); ========table2====== create table projects
 * ( proid number(5) primary key, proname varchar2(10) );
 * =======table3========== create table programmers_projects( programmer_id
 * number(5) references Programmers(pid), project_id number(5) references
 * Projects(proid), primary key(programmer_id,project_id) );
 * 
 * 
 */
