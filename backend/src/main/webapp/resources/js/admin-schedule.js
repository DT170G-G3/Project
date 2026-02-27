document.addEventListener('DOMContentLoaded', function() {

    /**
     * There's A LOT to change here later.
     */

    const NUMBER_OF_EMPLOYEES = 11;

    /**
     * Replace this with an actual list from the database.
     * @type {string[]}
     */
    const employees = [
        "Alice", "Bob", "Eve", "Mike", "Lisa",
        "Johan", "Elsa", "Lars", "Karin", "Emil", "Maria"
    ];

    /**
     * This isn't needed, we'll handle multiple weeks differently going forward.
     * @type {[{name: string, key: string, date: string},{name: string, key: string, date: string},{name: string, key: string, date: string},{name: string, key: string, date: string},{name: string, key: string, date: string},null]}
     */
    const days = [
        { name: "Måndag",   key: "Monday",    date: "16 Feb" },
        { name: "Tisdag",   key: "Tuesday",   date: "17 Feb" },
        { name: "Onsdag",   key: "Wednesday", date: "18 Feb" },
        { name: "Torsdag",  key: "Thursday",  date: "19 Feb" },
        { name: "Fredag",   key: "Friday",    date: "20 Feb" },
        { name: "Lördag",   key: "Saturday",  date: "21 Feb" },
    ];

    /**
     * This is just a placeholder - it's not needed in the final product.
     * @type {{Monday: {lunch: string[], evening: string[]}, Tuesday: {lunch: string[], evening: string[]}, Wednesday: {lunch: string[], evening: string[]}, Thursday: {lunch: string[], evening: string[]}, Friday: {lunch: string[], evening: string[]}, Saturday: {lunch: string[], evening: string[]}}}
     */
    const initialSchedule = {
        Monday:    { lunch: ["Alice", "Bob"],        evening: ["Mike", "Lisa", "Johan"] },
        Tuesday:   { lunch: ["Eve", "Elsa"],         evening: ["Lars", "Karin"] },
        Wednesday: { lunch: ["Alice", "Emil"],        evening: ["Bob", "Maria", "Mike"] },
        Thursday:  { lunch: ["Johan", "Eve"],         evening: ["Lisa", "Elsa"] },
        Friday:    { lunch: ["Karin", "Lars"],        evening: ["Alice", "Bob", "Eve"] },
        Saturday:  { lunch: ["Emil", "Maria"],        evening: ["Mike", "Johan", "Lars"] },
    };

    /**
     * Same, this is just a placeholder - not needed in the final product.
     * @type {{Monday: string[], Tuesday: string[], Wednesday: string[], Thursday: string[], Friday: string[], Saturday: string[]}}
     */
    const initialMenu = {
        Monday:    ["Pasta Carbonara", "Grillad Lax"],
        Tuesday:   ["Risotto ai Funghi", "Chicken Milanese"],
        Wednesday: ["Gnocchi al Pesto", "Branzino"],
        Thursday:  ["Tagliatelle Bolognese", "Veal Piccata"],
        Friday:    ["Linguine alle Vongole", "Ossobuco"],
        Saturday:  ["Ravioli Burro e Salvia", "Bistecca Fiorentina"],
    };

    /**
     * Might need to change this if someone has the same first name.
     * (First name + last name in the end, possibly?)
     * @param name
     * @returns {string}
     */
    function initials(name) {
        return name.at(0).toUpperCase();
    }

    /**
     * Get employee index from name. Will be changed later.
     * @param name
     * @returns {number}
     */
    function avatarIndex(name) {
        return employees.indexOf(name) % NUMBER_OF_EMPLOYEES;
    }

    function makeChip(name, inZone) {
        const idx = avatarIndex(name);
        const chip = document.createElement('div');
        chip.className = 'chip';
        chip.dataset.name = name;
        chip.innerHTML = `
        <span class="chip-avatar av-${idx}">${initials(name)}</span>
        ${name}
        ${inZone ? `<button class="remove-btn" title="Ta bort">✕</button>` : ''}
      `;
        if (inZone) {
            chip.querySelector('.remove-btn').addEventListener('click', e => {
                e.stopPropagation();
                chip.remove();
                updateEmptyHints();
            });
        }
        return chip;
    }

    function updateEmptyHints() {
        document.querySelectorAll('.shift-drop-zone').forEach(zone => {
            let hint = zone.querySelector('.empty-hint');
            const hasChips = zone.querySelectorAll('.chip').length > 0;
            if (hasChips) {
                if (hint) hint.remove();
            } else {
                if (!hint) {
                    hint = document.createElement('span');
                    hint.className = 'empty-hint';
                    hint.textContent = 'Dra hit en anställd!';
                    zone.appendChild(hint);
                }
            }
        });
    }

    const pool = document.getElementById('pool');
    employees.forEach(name => {
        pool.appendChild(makeChip(name, false));
    });

    const daysList = document.getElementById('days-list');

    days.forEach(day => {
        const sched = initialSchedule[day.key] || { lunch: [], evening: [] };

        const card = document.createElement('div');
        card.className = 'day-card';
        card.innerHTML = `
        <div class="day-header">
          <span class="day-name">${day.name}</span>
          <span class="day-date">${day.date}</span>
        </div>
        <div class="shifts">
          <div class="shift-row lunch">
            <div class="shift-label">
              <span class="shift-dot"></span>
              <span>Lunch</span>
            </div>
            <div class="shift-drop-zone lunch-zone" id="zone-${day.name}-lunch"></div>
          </div>
          <div class="shift-row evening">
            <div class="shift-label">
              <span class="shift-dot"></span>
              <span>Middag</span>
            </div>
            <div class="shift-drop-zone evening-zone" id="zone-${day.name}-evening"></div>
          </div>
        </div>
      `;
        daysList.appendChild(card);

        sched.lunch.forEach(name => {
            document.getElementById(`zone-${day.name}-lunch`).appendChild(makeChip(name, true));
        });
        sched.evening.forEach(name => {
            document.getElementById(`zone-${day.name}-evening`).appendChild(makeChip(name, true));
        });
    });

    Sortable.create(pool, {
        group: { name: 'staff', pull: 'clone', put: false },
        sort: false,
        animation: 150,
        ghostClass: 'sortable-ghost',
        dragClass: 'sortable-drag',
        onClone(evt) {
        }
    });

    document.querySelectorAll('.shift-drop-zone').forEach(zone => {
        Sortable.create(zone, {
            group: { name: 'staff', pull: true, put: true },
            animation: 150,
            ghostClass: 'sortable-ghost',
            dragClass: 'sortable-drag',
            onAdd(evt) {
                const chip = evt.item;
                if (!chip.querySelector('.remove-btn')) {
                    const btn = document.createElement('button');
                    btn.className = 'remove-btn';
                    btn.title = 'Ta bort';
                    btn.textContent = '✕';
                    btn.addEventListener('click', e => {
                        e.stopPropagation();
                        chip.remove();
                        updateEmptyHints();
                    });
                    chip.appendChild(btn);
                }
                updateEmptyHints();
            },
            onRemove() {
                updateEmptyHints();
            }
        });

        zone.addEventListener('dragover', () => zone.classList.add('drag-over'));
        zone.addEventListener('dragleave', () => zone.classList.remove('drag-over'));
        zone.addEventListener('drop', () => zone.classList.remove('drag-over'));
    });

    updateEmptyHints();

    document.querySelector('.btnPrimary').addEventListener('click', () => {
        const result = {};
        days.forEach(day => {
            result[day.name] = {
                lunch:   [...document.querySelectorAll(`#zone-${day.name}-lunch .chip`)].map(c => c.dataset.name),
                evening: [...document.querySelectorAll(`#zone-${day.name}-evening .chip`)].map(c => c.dataset.name),
            };
        });
        /**
         * "Save" to console for now. This needs to be serialized later, in order to be usable against the
         * backend and database.
         */
        console.log('Sparat schema:', result);
    });
});