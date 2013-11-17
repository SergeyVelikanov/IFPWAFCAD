package com.ifpwafcad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import com.model.Counselor;
import com.model.Subject;

/**
 * @author sergio
 * 
 */
public class Conex implements Serializable{
	private static final long serialVersionUID = 3396576446312056107L;
	private Map<Short, String> subjectsName; // read only
	private String subjectNameSelected;
	private Short subjectIdSelected;
	private String counselorName;
	private String counselorSurname;
	private String counselorNick;
	private String description;
	private Date memberSince;
	private String phone;
	private String email;
	private String councelorInfo = "";	
	private List<Query> list;

	@SuppressWarnings("unchecked")
	public Conex() {
		subjectsName = new LinkedHashMap<Short, String>();		
		list = new ArrayList<Query>();
		Session session = HibernateUtil.getSessionFactory().openSession();		
		// get all persistent objects of Subject
		Query query = session.createQuery("from Subject");
		list = query.list();
		// fill Map
		for (Iterator<Query> iterator = list.iterator(); iterator.hasNext();) {
			Subject subj = (Subject) iterator.next();
			subjectsName.put(subj.getSubjectId(), subj.getName());
		}
		session.close();

	}

	public void prerender() {
		Session session = HibernateUtil.getSessionFactory().openSession();	
		// get Subject instance selected in HTML form
		Subject sub = (Subject) session.get(Subject.class, subjectIdSelected);
		// get Counselor fk in Subject table for Counselor table
		Short fkCounselor = sub.getCounselorIdfk();
		// get Counselor instance corresponding to the selected Subject
		Counselor cons = (Counselor) session.get(Counselor.class, fkCounselor);
		counselorName = cons.getFirstName();
		counselorSurname = cons.getLastName();
		counselorNick = cons.getNickName();
		this.description = sub.getDescription();
		memberSince = cons.getMemberSince();
		phone = cons.getTelephone();
		email = cons.getEmail();
		councelorInfo += counselorName + " " + counselorNick + " "
				+ counselorSurname;
		session.close();
	}

	// Getters and Setters
	// ==============================================================================================
	public Short getSubjectIdSelected() {
		return subjectIdSelected;
	}

	public void setSubjectIdSelected(Short subjectIdSelected) {
		this.subjectIdSelected = subjectIdSelected;
		// call prerender method to get councelor info
		prerender();
		// set the selected name that selected id corresponds to
		setSubjectNameSelected(subjectsName.get(this.subjectIdSelected));
	}

	public String getSubjectNameSelected() {
		return subjectNameSelected;
	}

	public void setSubjectNameSelected(String subjectNameSelected) {
		this.subjectNameSelected = subjectNameSelected;
	}

	public String getCounselorName() {
		return counselorName;
	}

	public void setCounselorName(String counselorName) {
		this.counselorName = counselorName;
	}

	public String getCounselorNick() {
		return counselorNick;
	}

	public void setCounselorNick(String counselorNick) {
		this.counselorNick = counselorNick;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCounselorSurname() {
		return counselorSurname;
	}

	public void setCounselorSurname(String counselorSurname) {
		this.counselorSurname = counselorSurname;
	}

	public String getCouncelorInfo() {
		return councelorInfo;
	}

	public void setCouncelorInfo(String councelorInfo) {
		this.councelorInfo = councelorInfo;
	}

	public Map<Short, String> getSubjectsName() {
		return subjectsName;
	}

}
