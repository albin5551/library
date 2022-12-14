import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav-user',
  templateUrl: './nav-user.component.html',
  styleUrls: ['./nav-user.component.css']
})
export class NavUserComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  logout() {
    this.router.navigate(['/login']);
    localStorage.clear();
    }
}
