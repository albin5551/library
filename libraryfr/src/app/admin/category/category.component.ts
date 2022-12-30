import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminServiceService } from 'src/app/service/admin/admin-service.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {


  constructor(private adminService:AdminServiceService,private router:Router ) { }
  
  addcategory:FormGroup=new FormGroup(
    {
      categoryName:new FormControl('',[Validators.required])
     
    }
  )
  ngOnInit(): void {
    
  }

  onSubmit() {
    if(this.addcategory.valid){
      this.adminService.addCategory(this.addcategory.value).subscribe(result=>{
        if(result.categoryId){
          console.log(result);
          alert("category added");
        }
        else{
        alert("Error");
        }
      })
    }
  }

}
