document.addEventListener('DOMContentLoaded', function() {

    document.querySelectorAll('#pool .chip').forEach((chip, idx) => {
        const name = chip.dataset.name;
        chip.innerHTML = `
            <span class="chip-avatar av-${idx % 11}">${name.at(0).toUpperCase()}</span>
            ${name}
        `;
    });

    document.querySelectorAll('.shift-drop-zone .chip').forEach((chip, idx) => {
        const name = chip.dataset.name;
        chip.innerHTML = `
            <span class="chip-avatar av-${idx % 11}">${name.at(0).toUpperCase()}</span>
            ${name}
            <button class="remove-btn" title="Ta bort">✕</button>
        `;
        chip.querySelector('.remove-btn').addEventListener('click', e => {
            e.stopPropagation();
            const shiftId = chip.closest('.shift-drop-zone').dataset.shiftId;
            const empId = chip.dataset.id;
            triggerBackend('remove', empId, shiftId);
            chip.remove();
            updateEmptyHints();
        });
    });

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

    function triggerBackend(action, empId, shiftId) {
        document.getElementById('scheduleForm:empId').value = empId;
        document.getElementById('scheduleForm:shiftId').value = shiftId;
        if (action === 'add') {
            document.getElementById('scheduleForm:addBtn').click();
        } else {
            document.getElementById('scheduleForm:removeBtn').click();
        }
    }

    const pool = document.getElementById('pool');

    Sortable.create(pool, {
        group: { name: 'staff', pull: 'clone', put: false },
        sort: false,
        animation: 150,
        ghostClass: 'sortable-ghost',
        dragClass: 'sortable-drag',
        onClone(evt) {}
    });

    document.querySelectorAll('.shift-drop-zone').forEach(zone => {
        Sortable.create(zone, {
            group: { name: 'staff', pull: true, put: true },
            animation: 150,
            ghostClass: 'sortable-ghost',
            dragClass: 'sortable-drag',
            onAdd(evt) {
                const chip = evt.item;
                const name = chip.dataset.name;
                const targetZone = evt.to;
                const sourceZone = evt.from;

                const existing = [...targetZone.querySelectorAll('.chip')].filter(c => c !== chip && c.dataset.name === name);
                if (existing.length > 0) {
                    if (sourceZone === pool) {
                        chip.remove();
                    } else {
                        sourceZone.appendChild(chip);
                    }
                    updateEmptyHints();
                    return;
                }

                // Add remove button if not present
                if (!chip.querySelector('.remove-btn')) {
                    const btn = document.createElement('button');
                    btn.className = 'remove-btn';
                    btn.title = 'Ta bort';
                    btn.textContent = '✕';
                    btn.addEventListener('click', e => {
                        e.stopPropagation();
                        const shiftId = chip.closest('.shift-drop-zone').dataset.shiftId;
                        const empId = chip.dataset.id;
                        triggerBackend('remove', empId, shiftId);
                        chip.remove();
                        updateEmptyHints();
                    });
                    chip.appendChild(btn);
                }

                // Trigger add on backend
                const shiftId = targetZone.dataset.shiftId;
                const empId = chip.dataset.id;
                triggerBackend('add', empId, shiftId);

                // If moved from another zone, trigger remove from source
                if (sourceZone !== pool) {
                    const sourceShiftId = sourceZone.dataset.shiftId;
                    triggerBackend('remove', empId, sourceShiftId);
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
});