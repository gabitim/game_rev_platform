--insert in genre
INSERT INTO genre VALUES(1, 'A', 'action');
INSERT INTO genre VALUES(2, 'G', 'generic');
INSERT INTO genre VALUES(3, 'M', 'moba');
INSERT INTO genre VALUES(4, 'R', 'rpg');
INSERT INTO genre VALUES(5, 'S', 'simulation');
INSERT INTO genre VALUES(6, 'SH', 'shooting');
INSERT INTO genre VALUES(7, 'P', 'platform');

--insert in game
INSERT INTO game VALUES(NULL, 'Assassins Creed: Valhalla', 
'With an action that takes place in 873 AD, the game presents an alternate history of the Viking invasions in Britain . The player controls Eivor, a Viking fighterwho ends up in the middle of the conflict between the Brotherhood of Assassins and the Templar Order. ',
TO_DATE('2020/11/12 12:00:00', 'yyyy/mm/dd hh24:mi:ss'), 4.2, 
'Assassins Creed Valhalla is out today, and the world of Vikings and clansmen are set to invade the lush green hills of England demanding all sorts of treasures and valuables');

INSERT INTO game VALUES(NULL, 'Cyberpunk 2077', 
'Adapted from the Cyberpunk franchise, the story takes place in dystopian Night City, an open world with six distinct regions. Players assume the first-person perspective of a customisable mercenary known as V, who can acquire skills in hacking and machinery, an arsenal of ranged weapons, and options for melee combat.',
TO_DATE('2020/11/20', 'yyyy/mm/dd '), 4.4, 
'In the wake of the surprise third delay for Cyberpunk 2077, it was revealed that the reason for it was due to current-gen console performance, and that the game was “ready for the PC”.');

INSERT INTO game VALUES(NULL, 'Call of Duty: Cold War', 
'Call of Duty: Cold War is a 2020 first-person shooter video game developed by Treyarch and Raven Software and published by Activision. It is the sixth installment in the Black Ops series, and the seventeenth installment in the overall Call of Duty series.',
TO_DATE('2020/10/30', 'yyyy/mm/dd '), 4.2, 
'Call of Duty Cold War is finally here, bringing us an all new COD experience filled with lots of adrenaline-fueled excitement.');

INSERT INTO game VALUES(NULL, 'FIFA 21', 
'FIFA 21 is a football simulation video game published by Electronic Arts as part of the FIFA series.[1] It is the 28th installment in the FIFA series, and was released 9 October 2020 for Microsoft Windows, Nintendo Switch, PlayStation 4 and Xbox One.',
TO_DATE('2020/10/9', 'yyyy/mm/dd '), 3.4, 
'Its another year and another FIFA game is out now, EA previously said that this years FIFA 21 wont be the next-gen version of the title, but how does it perform');

INSERT INTO game VALUES(NULL, 'Microsoft Flight Simulator', 
'Microsoft Flight Simulator is a series of amateur flight simulator programs for Microsoft Windows operating systems. It is one of the longest-running, best-known, and most comprehensive home flight simulator programs on the market. It was an early product in the Microsoft application portfolio and differed significantly from Microsofts other software, which was largely business-oriented.',
TO_DATE('2020/07/14', 'yyyy/mm/dd '), 4.2, 
NULL);

INSERT INTO game VALUES(NULL, 'Watch Dogs Legion', 
'Watch Dogs: Legion is a 2020 action-adventure game published by Ubisoft. It is the third instalment in the Watch Dogs series, and the sequel to 2016s Watch Dogs 2. Set within a fictionalised representation of a future, dystopian London, the games story focuses on the hacker group DedSec as they seek to clear their names for a series of bombings they have been framed for',
TO_DATE('2020/10/12', 'yyyy/mm/dd '), 4.1, 
NULL);

--insert in genregame
INSERT INTO genregame VALUES(1,6);
INSERT INTO genregame VALUES(5,5);
INSERT INTO genregame VALUES(5,4);
INSERT INTO genregame VALUES(2,4);
INSERT INTO genregame VALUES(1,3);
INSERT INTO genregame VALUES(6,3);
INSERT INTO genregame VALUES(1,2);
INSERT INTO genregame VALUES(6,2);
INSERT INTO genregame VALUES(1,1);
INSERT INTO genregame VALUES(4,1);
INSERT INTO genregame VALUES(2,1);


--insert in group
INSERT INTO groupp VALUES(1, 'T', 'toddler: all new users have this level');
INSERT INTO groupp VALUES(2, 'Y', 'youngster: users with min 5 played games and 3 posts');
INSERT INTO groupp VALUES(3, 'J', 'junior: users with min 50 played games and 30 posts');
INSERT INTO groupp VALUES(4, 'O', 'officer: users with min 100 played games and 70 posts');
INSERT INTO groupp VALUES(5, 'S', 'senior: users with min 200 played games and 100 posts');
INSERT INTO groupp VALUES(6, 'H', 'hero: users with min 500 played games and 200 posts');
INSERT INTO groupp VALUES(7, 'L', 'legend: users with min 1000 played games');
INSERT INTO groupp VALUES(8, 'K', 'knight: users with min 500 posts');
INSERT INTO groupp VALUES(9, 'D', 'duke: users with important contributions to the platform');
INSERT INTO groupp VALUES(10, 'F', 'founder: the one and only!!');

--insert in role
INSERT INTO role VALUES(1, 'U', 'user');
INSERT INTO role VALUES(2, 'M', 'moderator');
INSERT INTO role VALUES(3, 'A', 'admin');

--insert in permission
INSERT INTO permission VALUES(1, 'P', 'post a comment, review');
INSERT INTO permission VALUES(2, 'A', 'add a game');
INSERT INTO permission VALUES(3, 'B', 'block a user, cant post comments/review anymore');
INSERT INTO permission VALUES(4, 'D1', 'delete your own comment/review');
INSERT INTO permission VALUES(5, 'D2', 'delete other comments');
INSERT INTO permission VALUES(6, 'D3', 'delete a game');
INSERT INTO permission VALUES(7, 'E', 'edit a game');
INSERT INTO permission VALUES(8, 'R', 'remove/ban a user');

--insert in rolePermission
--user permissions:
INSERT INTO rolepermission VALUES(1,1);
INSERT INTO rolepermission VALUES(1,4);

--moderator permissions:
INSERT INTO rolepermission VALUES(2,1);
INSERT INTO rolepermission VALUES(2,2);
INSERT INTO rolepermission VALUES(2,3);
INSERT INTO rolepermission VALUES(2,4);
INSERT INTO rolepermission VALUES(2,5);
INSERT INTO rolepermission VALUES(2,7);

--admin permissions
INSERT INTO rolepermission VALUES(3,1);
INSERT INTO rolepermission VALUES(3,2);
INSERT INTO rolepermission VALUES(3,3);
INSERT INTO rolepermission VALUES(3,4);
INSERT INTO rolepermission VALUES(3,5);
INSERT INTO rolepermission VALUES(3,6);
INSERT INTO rolepermission VALUES(3,7);
INSERT INTO rolepermission VALUES(3,8);

--insert in userr
INSERT INTO userr VALUES(NULL, 'Gabi', 'Popescu', '1d9a4e369d942566b713f31955de222a836521f9', 'gabipop12@gmail.com', TO_DATE('2020/11/28', 'yyyy/mm/dd '), 10, 3);
INSERT INTO userr VALUES(NULL, 'Silviu', 'Gibon', 'd02a11df23277353fa9fa168f728c8e745ecedb3', 'silviaaa@gmail.com', TO_DATE('2020/11/28', 'yyyy/mm/dd '), 9, 2);
INSERT INTO userr VALUES(NULL, 'Alex', 'Mumu', '05fc7b2e03905a116ce269700c37bdcd66234719', 'muma@yahoo.com', TO_DATE('2020/11/29', 'yyyy/mm/dd '), 1, 1);
INSERT INTO userr VALUES(NULL, 'Gica', 'Gachi', 'df211ccdd94a63e0bcb9e6ae427a249484a49d60', 'ggai@gmail.com', TO_DATE('2020/11/28', 'yyyy/mm/dd '), 1, 1);
INSERT INTO userr VALUES(NULL, 'Munica', 'Arlabesu', '7ddbb6309d14a74d92e31b26f3ff5454dfa0708b', 'muiar@gmail.com', TO_DATE('2020/11/27', 'yyyy/mm/dd '), 1, 1);
INSERT INTO userr VALUES(NULL, 'Doina', 'Bunkar', 'f28b742be7ef215a91c58f9647adbdddce26f30b', 'ddbbkkrr@gmail.com', TO_DATE('2020/11/26', 'yyyy/mm/dd '), 1, 1);
INSERT INTO userr VALUES(NULL, 'Cristi', 'Frrwezxc', '50e8ad82b19d931bf522725b429408902506bf63', 'cristi2341@gmail.com', TO_DATE('2020/10/25', 'yyyy/mm/dd '), 1, 1);
INSERT INTO userr VALUES(NULL, 'Bunk', 'merkuesc', 'as0e8ad82b1sa9d931bf522725b429408902506bf6adg', 'bunk@gmail.com', TO_DATE(SYSDATE, 'yyyy/mm/dd'), 1, 1);

--insert in gamesession
INSERT INTO gamesession VALUES(NULL, 'Noice game', TO_DATE('2020/11/27 21:02:44', 'yyyy/mm/dd hh24:mi:ss'), 2, 1);
INSERT INTO gamesession VALUES(NULL, 'SO CLOSEEE!!', TO_DATE('2020/11/28 02:00:44', 'yyyy/mm/dd hh24:mi:ss'), 3,1 );
INSERT INTO gamesession VALUES(NULL, 'Victoryy!', TO_DATE('2020/11/28 03:02:12', 'yyyy/mm/dd hh24:mi:ss'), 1, 1);
INSERT INTO gamesession VALUES(NULL, 'asdgsags', TO_DATE('2020/11/28 01:23:45', 'yyyy/mm/dd hh24:mi:ss'), 5, 4);
INSERT INTO gamesession VALUES(NULL, NULL, TO_DATE('2020/11/28 00:12:52', 'yyyy/mm/dd hh24:mi:ss'), 1, 3);
INSERT INTO gamesession VALUES(NULL, 'gooood', TO_DATE('2020/11/28 12:34:20', 'yyyy/mm/dd hh24:mi:ss'), 2, 2);
INSERT INTO gamesession VALUES(NULL, 'this is the worst gameee', TO_DATE('2020/11/27 05:04:54', 'yyyy/mm/dd hh24:mi:ss'), 5, 1);

--insert in review
INSERT INTO review VALUES(NULL, 'Valhalla review', 'awsome review ', 1, 3, NULL, TO_DATE(sysdate));
INSERT INTO review VALUES(NULL, 'money Mow', 'random review. tried to download this game on torrent. Bad experience, the game was very hard to find hacked', 5, 2, NULL, TO_DATE(sysdate));
INSERT INTO review VALUES(NULL, NULL, 'it was realistic enough to be used for real-life flight training.', 5, 4, NULL, TO_DATE(sysdate));
INSERT INTO review VALUES(NULL, 'Random comment', 'this a random comment ', 2, 4, NULL, TO_DATE(sysdate));
INSERT INTO review VALUES(NULL, 'My review', 'FIFA IS BETTER THAN PES. AND THATs A FACT!!!!', 4, 1, NULL, TO_DATE(sysdate));
INSERT INTO review VALUES(NULL, 'cyberpunkyeas', 'released in 2020', 2, 3, NULL, TO_DATE(sysdate));
INSERT INTO review VALUES(NULL, NULL, 'i think that this game is overrated', 2, 7, NULL, TO_DATE(sysdate));
INSERT INTO review VALUES(NULL, NULL, 'hahaha. Bad bad game', 3, 6, NULL, TO_DATE(sysdate));


