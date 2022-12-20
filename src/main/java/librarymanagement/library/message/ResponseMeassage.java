package librarymanagement.library.message;

public class ResponseMeassage {
    private String message;
	private String fileDownloadUri;
	

	//   public ResponseMessage(String message, String fileDownloadUri) {
	//     this.message = message;
	//     this.fileDownloadUri = fileDownloadUri;
	//   }



    public ResponseMeassage(String message, String fileDownloadUri) {
        this.message = message;
        this.fileDownloadUri = fileDownloadUri;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public String getFileDownloadUri() {
        return fileDownloadUri;
    }


    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }




    
}
