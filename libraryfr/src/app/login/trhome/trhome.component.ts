import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trhome',
  templateUrl: './trhome.component.html',
  styleUrls: ['./trhome.component.css']
})
export class TrhomeComponent implements OnInit {

  showNavigationIndicators = false;
	images = [944, 1011, 984].map((n) => `https://picsum.photos/id/${n}/900/500`);
  constructor() { }

  ngOnInit(): void {

  }

  

}
