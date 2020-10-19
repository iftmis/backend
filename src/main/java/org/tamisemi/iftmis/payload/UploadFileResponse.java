package org.tamisemi.iftmis.payload;

import org.springframework.http.ResponseEntity;
import org.tamisemi.iftmis.service.dto.FileResourceDTO;

public class UploadFileResponse {


	private String fileName;	
    private String fileDownloadUri;
    private String fileType;
    private String fileExtension;
    private long size;
    private String md5;
    private FileResourceDTO fileResourceDTO;
    

	public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, String fileExtension, long size,
			String md5, FileResourceDTO fileResourceDTO) {
		super();
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.fileExtension = fileExtension;
		this.size = size;
		this.md5 = md5;
		this.fileResourceDTO = fileResourceDTO;
	}
	
	public UploadFileResponse(String fileName2, String fileDownloadUri2, String contentType, String extension,
			long size2, String md52, ResponseEntity<FileResourceDTO> result) {
		// TODO Auto-generated constructor stub
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public FileResourceDTO getFileResourceDTO() {
		return fileResourceDTO;
	}
	public void setFileResourceDTO(FileResourceDTO fileResourceDTO) {
		this.fileResourceDTO = fileResourceDTO;
	}
   
	
}