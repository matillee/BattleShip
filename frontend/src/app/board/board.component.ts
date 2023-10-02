import { Component } from '@angular/core';

export interface Square {
  text: string;
  //state: string; TODO: Rendering css class change?
}

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent {

  squares: Square[] = [
    {text: 'Tile 1'},
    {text: 'Tile 2'},
    {text: 'Tile 3'},
    ];

    onClick(square: Square){
      square.text ="Click"
    }

}
