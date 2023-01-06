import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';

@Component({
  selector: 'app-viewpg-test',
  templateUrl: './viewpg-test.component.html',
  styleUrls: ['./viewpg-test.component.css']
})
export class ViewpgTestComponent implements OnInit {

  selectedFiles: any;
  currentFile: any;
ser() {
throw new Error('Method not implemented.');
}
  result: any;
  i: any = 0;
  pageNo: any = 1;
  sortBy: any = "book_id";
  paginated: any;
  bookDetail: any;
  tableSize: number = 10;
  page: number = 1
  count: any;
  category:any;
  constructor(private adminService: AdminServiceService) { }

  search:FormGroup=new FormGroup({
    inp:new FormControl()
  })

  // ab:FormGroup=new FormGroup({
  //   check:new FormControl()
  // })

  ngOnInit(): void {
    this.adminService.pagenate(this.page, this.tableSize, this.sortBy).subscribe(response => {
      this.result = response.content;
      this.count = response.totalElements;
      console.log(this.result);
      console.log(this.count)
    
    });

    this.adminService.categoryView().subscribe(res=>{
      this.category=res;
      console.log(this.category)
    })



    // this.adminService.getBook().subscribe(result=>{
    //   this.bookDetail=result;
    //   console.log(this.bookDetail);
    // })

  }
  gototop(){
      window.scrollTo({
        top:0,
        left:0,
        behavior:'smooth'
      })
  }


  // paginate(pageNO:any,pageSize:any,sortBy:any){

  //   this.i=pageNO;
  //   this.sortBy=sortBy;
  //   this.adminService.pagenate(pageNO,pageSize,sortBy).subscribe(
  //     response=>{
  //       this.result=response;
  //       this.count=response.toalElements;
  //       console.log(this.paginated)
  //     }
  //   )

  // }

  onTableDataChange(event: any) {

    this.adminService.pagenate(event, this.tableSize, this.sortBy).subscribe(
      response => {
        this.result = response.content;
        // this.count=response.toalElements;
        console.log(this.paginated)
      }
    )


  }


  selectFile($event:any) {
    this.selectedFiles=$event.target.files;
     }
     
     upload(): void {
   
       if (this.selectedFiles) {
         const file: File | null = this.selectedFiles.item(0);
   
         if (file) {
           this.currentFile = file;
           this.adminService.upload(this.currentFile).subscribe(res=>{
             console.log(res);
             if(res!==null){
               alert(res.message);
               window.location.reload()
             }
           })
   
         //   this.adminService.uploadCsv(this.currentFile).subscribe({
         //    next: (event: any) => {
         //       if (event.type === HttpEventType.UploadProgress) {
         //         console.log(Math.round(100 * event.loaded / event.total));
   
         //       } else if (event instanceof HttpResponse) {
         //         this.message = event.body.responseMessage;
         //       }
         //     },
         //     error:(err: any) => {
         //       console.log(err);
   
         //       if (err.error && err.error.responseMessage) {
         //         this.errorMsg = err.error.responseMessage;
         //       } else {
         //         this.errorMsg = 'Error occurred while uploading a file!';
         //       }
   
         //       this.currentFile = undefined;
         // }});
         }
   
         this.selectedFiles = undefined;
       }
     }

     cate(id: any[]) {
      console.log('++++++',id);
        
      this.adminService.bookByCategory(id,this.pageNo,this.tableSize,"").subscribe(res=>{
        this.result = res.content;
        this.count = res.totalElements;
        console.log(this.result);
        console.log(this.count)
      })
      
      }


   


}
