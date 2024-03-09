package br.com.siswbrasil.escritorio.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Tracking implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID_USER_INSERT", length = 30)
	private Long idUserInsert;

	@Column(name = "ID_USER_UPDATE", length = 30)
	private Long idUserUpdate;

	@Column(name = "DT_INSERT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtInsert;

	@Column(name = "DT_UPDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtUpdate;

	@PrePersist
	public void onPrePersist() {
		this.dtInsert = new Date();
		this.idUserInsert = 1l;
	}

	@PreUpdate
	public void onPreUpdate() {
		if (this.dtInsert == null) {
			this.dtInsert = new Date();
		}
		this.dtUpdate = new Date();
		this.idUserUpdate = this.idUserInsert = 1l;
	}

}
