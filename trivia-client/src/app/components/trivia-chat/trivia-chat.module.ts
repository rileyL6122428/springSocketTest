import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TriviaChatComponent } from './trivia-chat.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
  ],
  exports: [ TriviaChatComponent ],
  declarations: [ TriviaChatComponent ]
})
export class TriviaChatModule { }
