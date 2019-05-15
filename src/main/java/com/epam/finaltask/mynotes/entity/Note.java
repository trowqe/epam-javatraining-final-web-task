package com.epam.finaltask.mynotes.entity;

import java.io.Serializable;

public class Note implements Serializable {
	private static final long serialVersionUID = -4641414910734795519L;

	private int idnote;
	private String title;
	private String text; // how should i store the text?
	private String created; // date to string
//	private String preview; 		//url to picture
	private byte[] preview;
	private String previewEnc;
	private int canAccess; // if accessable
	private int iduser; // id of creator

	public Note() {
	}

	public String getPreviewEnc() {
		return previewEnc;
	}

	public void setPreviewEnc(String previewEnc) {
		this.previewEnc = previewEnc;
	}

	public Note(int idnote, String title, String text, String created, byte[] preview, int canAccess, int iduser) {

		this.idnote = idnote;
		this.title = title;
		this.text = text;
		this.created = created;
		this.preview = preview;
		this.canAccess = canAccess;
		this.iduser = iduser;

	}

	public int getIdnote() {
		return idnote;
	}

	public void setIdnote(int idnote) {
		this.idnote = idnote;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public byte[] getPreview() {
		return preview;
	}

	public void setPreview(byte[] preview) {
		this.preview = preview;
	}

	public int getCanAccess() {
		return canAccess;
	}

	public void setCanAccess(int canAccess) {
		this.canAccess = canAccess;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + canAccess;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + idnote;
		result = prime * result + iduser;
		result = prime * result + ((preview == null) ? 0 : preview.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (canAccess != other.canAccess)
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (idnote != other.idnote)
			return false;
		if (iduser != other.iduser)
			return false;
		if (preview == null) {
			if (other.preview != null)
				return false;
		} else if (!preview.equals(other.preview))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Note [idnote=" + idnote + ", title=" + title + ", text=" + text + ", created=" + created + ", preview="
				+ preview + ", previewEnc=" + previewEnc + ", canAccess=" + canAccess + ", iduser=" + iduser + "]";
	}

}
