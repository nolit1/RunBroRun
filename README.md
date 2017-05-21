# RunBroRun
Название немного не соответствует игре, но раз уж назвал давно, да и в коде использовал имя, то пусть пока так будет.
Суть игры: нам нужно летать между шипов и набирать очки. Со временем расстояние между шипами уменьшается, и играть становится сложнее.
В конце концов расстояние становится очень маленьким и лишь ловкие игроки смогут поставить большой результат. 

На данный момент реализован весь механизм игры: запускаем, нас встречает надпись "GO", нажимаем мышкой и далее управляем стрелками.
Когда проигрываем, если набрали рекорд - одна надпись, не набрали - другая. Нажимаем - играем снова. На данный момент ДЛЯ УДОБСТВА
тестирования "рекорд" перезаписывается с каждым запуском игры. То есть если не закрывать игру, то будем ставить рекорд, бить его и т.д.
Чтобы рекорд сохранялся и при закрытии игры - нужно просто удалить одну строчку в коде. 

Из того, что нужно исправить: (просто нужно показать что я сделал, поэтому заливаю с небольшими багами, скоро всё исправлю)
1) Иногда не срабатывают столкновения персонажа и шипов. Исправляется не сложно, так что не проблема
2) Текстуры - самая простая часть, найду что-то более качественное или нарисую сам - просто заменю файл в проекте и хорошо. Пока что
там нарисованные "на колене" для тестирования.
3) Смерть - это из разряда сделаю чтобы было интереснее. Когда персонаж врезается в шипы, они улетят налево за экран, как будто мы потеряли
их из кадра, и после этого экран с результатом. Просто будет чуть динамичнее смотреться.
4) Сложность. Пока что игра простая, хочу либо увеличить скорость движения шипов справа налево, либо ещё что-то такое. Но точно исправлю:
сейчас шипы "сжимаются" при каждом получении очка. Сделаю чтобы они сжимались например каждые 5 очков, или что-то подобное. Опять же чтобы
интереснее было.

Теперь немного технической части:

Проект создавался как универсальный для нескольких платформ, а именно desktop и android. Мы пишем всё в привычном коде, используя
библиотеку badlogic.gdx, и libgdx сама оптимизирует код для платформ. Но сейчас я на андроид закрыл глаза, ибо ноут не тянет эмулятор,
решил не париться и сделать просто для пк. Залилась ещё версия Html - на неё вообще можно не смотреть, галочку убрать забыл когда создавал.

Собственно так как создавалось и на андроид, то использовал фишку которую в интернете нашёл, чтобы экран подгонялся по размерам под любое
устройство. Именно для этого над фоном картинкой сверху и снизу есть как бы однотонно закрашенные прямоугольники. Сейчас это уже не имеет
значение, просто чтобы не было недопонимания, откуда это взялось и для чего.
Файлы (текстуры, звуки) лежат в папке Android-assets-data

Немного из внутринностей:

Код пишется в папке Core-src...
Есть класс персонажа(bro), в котором прописаны все его возможности.
Scrollaиду и ScrollHandler отвечают за объекты, которые двигаются справа налево, их коллизии, расположение и подобное.
Thorns - шипы, собственно класс шипов
GameRender и GameWorld - базовые классы, вряд ли нуждаются в комментарии
AssetLoader - здесь работа с текстурами, звуками - файлам в общем
InputHandler - нажатия на клавиатуру, мышь - взаиводействие пользвоателя с игрой
GameScreen - gameScreen. Тоже базовый класс

Шрифт. Шрифт - наверное самое красивое в моей игре ахах. Не скрываю, смотрел в интернете как это делается, однако выглядит
как мне кажется классно. Не думаю что это плохо.

Трудные моменты и не очень старался комментировать в коде, но вроде там всё максимально просто, не считая некоторых моментов.

В общем игровой процесс готов, осталось только зафиксить некоторые баги и сделать игру чуть красивее и сложнее, но это уже мелочи.
Думаю над тем, чтобы управление вверх и вниз осуществлялось с помощью мыши, ибо так точнее получится проходить препятствия когда совсем
узко.
