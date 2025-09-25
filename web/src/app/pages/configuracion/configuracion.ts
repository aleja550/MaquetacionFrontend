import { Component } from '@angular/core';

@Component({
  selector: 'app-configuracion',
  imports: [],
  templateUrl: './configuracion.html',
  styleUrl: './configuracion.css'
})
export class Configuracion {
  protected onPrivacyClick(): void {
    console.log('Privacy settings clicked');
  }

  protected onThemeClick(): void {
    console.log('Theme settings clicked');
  }

  protected onFeedbackClick(): void {
    console.log('Send feedback clicked');
  }

  protected onHelpClick(): void {
    console.log('Help clicked');
  }
}
