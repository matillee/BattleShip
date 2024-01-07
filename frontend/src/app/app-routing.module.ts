import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LandingComponent } from './landing/landing.component';
import { HighscoreComponent } from './highscore/highscore.component';
import { BoardComponent } from './board/board.component';

const routeConfig: Routes = [
  {
    path: '',
    component: LandingComponent,
    title: 'Landing',
  },
  {
    path: 'highscore',
    component: HighscoreComponent,
    title: 'Highscore',
  },
  {
    /* path: 'board/:id', */
    path: 'board',
    component: BoardComponent,
    title: 'Board',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routeConfig)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
