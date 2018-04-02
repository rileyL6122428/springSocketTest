import { Observer } from 'rxjs/Observer';
import { Observable } from 'rxjs/Observable';
import { Subscription } from 'rxjs/Subscription';
import { Nameable } from '../../domain/base/nameable';

export class Store<T extends Nameable> {

    protected records: Map<string, T>;
    protected dispatcher: Observer<Store<T>>;
    protected updates: Observable<Store<T>>;

    constructor() {
        this.records = new Map<string, T>();
        this.updates = new Observable<Store<T>>((observer) => {
            this.dispatcher = observer;
        });
    }

    placeListener(listener: any): Subscription {
      listener(this);
      return this.updates.subscribe(() => listener(this));
    }

    depositList(records: Array<T>): void {
        records.forEach((record) => this.insertRecord(record));
        this.emitUpdate();
    }

    deposit(record: T): void {
        this.insertRecord(record);
        this.emitUpdate();
    }

    private insertRecord(record: T): void {
        this.records.set(record.name, record);
    }

    private emitUpdate(): void {
        this.dispatcher.next(this);
    }

    recordsAsList(): Array<T> {
        const recordsList = new Array<T>();
        this.records.forEach((record: T, name: string) => {
            recordsList.push(record);
        });
        return recordsList;
    }

}
