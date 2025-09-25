import { Component, signal } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-alarma-matematica',
  imports: [CommonModule, FormsModule],
  templateUrl: './alarma-matematica.html',
  styleUrl: './alarma-matematica.css'
})
export class AlarmaMatematica {
  num1 = signal(0);
  num2 = signal(0);
  correctAnswer = signal(0);
  userAnswer = '';
  reminderTitle = '30 minutos de cardio';
  reminderTime = '6:00 AM';
  category = 'Ejercicio';

  constructor(private router: Router) {
    this.generateProblem();
  }

  generateProblem() {
    const n1 = Math.floor(Math.random() * 50) + 10;
    const n2 = Math.floor(Math.random() * 50) + 10;
    this.num1.set(n1);
    this.num2.set(n2);
    this.correctAnswer.set(n1 + n2);
  }

  onConfirm() {
    const answer = parseInt(this.userAnswer);
    if (answer === this.correctAnswer()) {
      this.router.navigate(['/dashboard']);
    } else {
      alert('Respuesta incorrecta. Intenta de nuevo.');
      this.userAnswer = '';
      this.generateProblem();
    }
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
