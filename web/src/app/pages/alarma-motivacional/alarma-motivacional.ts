import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-alarma-motivacional',
  imports: [CommonModule, FormsModule],
  templateUrl: './alarma-motivacional.html',
  styleUrl: './alarma-motivacional.css'
})
export class AlarmaMotivacional {
  reminderTitle = 'Tomar Medicamento';
  reminderTime = '9:00 AM';
  category = 'Medicamentos';
  motivationalPhrase = 'Hoy voy a lograr mis metas';
  userAnswer = '';

  constructor(private router: Router) {}

  onConfirm() {
    if (this.userAnswer.trim() === this.motivationalPhrase) {
      this.router.navigate(['/dashboard']);
    } else {
      alert('Debes escribir la frase exactamente como aparece.');
      this.userAnswer = '';
    }
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
