package com.skypro.java.petshelterbot.message;
/**
 * class  bot out message
 *
 * @author KiriukhinD
 */
public class BotOutMessages {
    //Консультация с новым пользователем
    public static final String NEW_USER_HELLO = "У тебя есть уникальная возможность совершить добрый поступок,"
                                    + " и стать обладателем домашнего питомца. ";
    public static final String NEW_USER_INFO_START = "Наш приют содержит более множество питомцев,"
                                    + "которые каждый день получают самую лучшую заботу в мире,"
                                    + "ещё наш приют обладает сервисом  помощи нашим подопечным,"
                                    + "прислать Вашу помощь  можно на номер 8 (54545)-5858-67858.";
    public static final String NEW_USER_INFO_SHELTER = "Наш приют работает каждый день с 8:00 до 20:00.\n"
                                    + "Мы находимся г. Алматы, ул. Приютная 1, строение 2.";
    public static final String NEW_USER_INFO_REGULATIONS = """
            На территории приюта действуют следующие правила:
            1 Нельзя курить.
            2 Нельзя распивать алкогольные напитки.
            3 Нельзя мусорить.
            4 Общайтесь с животными в присутствии волонтера.
            """;
    public static final String NEW_USER_CONTACT_INFO = "Перед тем как  взять питомца вы должны оставить свои контактные данные";
    public static final String NEW_USER_MESSAGE_VOLUNTEER = "!!!! Если вы не получили нужную информацию можете обратиться к волонтёру !!!\n /volunteer Вызвать волонтёра ";

    //Консультация с потенциальным хозяином животного из приюта
    public static final String POTENTIAL_ANIMAL_OWNER_INFO_HELLO = "Вы находитесь в меню информации про то как стать владельцем питомца";
    public static final String POTENTIAL_ANIMAL_OWNER_REGULATIONS_ANIMAL = " Правила знакомства с животным, прежде чем взять питомца из приюта.\n"
                                    + "Вам нужно познакомиться с питомцем и привыкнуть друг к другу "
                                    + "для этого необходимо, каждый день посещать приют." + " и кормить питомца всякими вкусняшками.";
    public static final String POTENTIAL_ANIMAL_OWNER_LIST_THE_DOCUMENTS = """
            Список документов, необходимых для того, чтобы взять животное из приюта.
            1 Паспорт.
            2 Рекомендации от 5 лучших друзей.
            """;
    public static final String POTENTIAL_ANIMAL_OWNER_RECOMMENDATIONS_TRANSPORT = " Рекомендаций по транспортировке животного.\n"
                                    + "Обязательно питомца нужно вести в клетке или на поводке.";
    public static final String POTENTIAL_ANIMAL_OWNER_LIST_RECOMMENDATIONS_HOME = """
            Список рекомендаций по обустройству дома для щенка.
            1 поставить миску.
            2 добавить место для сна.
            3 положить кость.""";
    public static final String POTENTIAL_ANIMAL_OWNER_LIST_RECOMMENDATIONS_HOME_BIG_DOG = """
            Список рекомендаций по обустройству дома для взрослой собаки.
            1 Крепкая цепь.
            2 Большая конура.
            3 Много еды.
            """;
    public static final String RECOMMENDATIONS_FOR_CAT_HOME = """
            Список рекомендаций по обустройству дома для кошки.
            1 поставить миску.
            2 добавить место для сна.
            3 положить кость.""";
    public static final String POTENTIAL_ANIMAL_OWNER_ADVICE_COMMUNICATION_ANIMAL = """
             Советы кинолога по первичному общению с собакой.
            1 Не смотри собаке в глаза.
            2 Подкорми собаку сладким.
            3 Погладь собаку.
            4 Поиграй с собакой.""";
    public static final String POTENTIAL_ANIMAL_OWNER_RECOMMENDATIONS_ON_CYNOLOGIST = """
            !!!!!!Список профессиональных кинологов!!!!!!
            1 Стрежонов Игорь  стаж  5 лет телефон 7 (98980)-6789-6566
            2 Волков Александр стаж  7 лет телефон 8 (543453)-9898-988
            3 Вортников Павел стаж  10 лет телефон 8 (785869)-989-989""";
    public static final String POTENTIAL_ANIMAL_OWNER_LIST_QUESTION = """
            ???Список вопросов к будущему хозяину питомца???
            1 В частный дом или квартиру хотите взять собаку.
            2 Есть ли опыт содержания собак.
            3  От чего умерла прежняя собака\s
             собаки и сколько было ей лет.
            4  Как часто выгуливают собаку (если собаку берут для содержания на цепи в будке или в вольере).
            5  Сколько лет человеку (если у собеседника молодой голос).
            6  Есть ли маленькие дети (если берут собаку в квартиру)
            7  Согласны ли другие члены семьи появлению собаки.
            8  Живут люди в собственном или съемном жилье
            9  Как относятся люди к самовыгулу собак.""";
    public static final String POTENTIAL_ANIMAL_OWNER_RENOUNCEMENT = """
            !!!!!!!   Почему могут отказать в передаче питомца    !!!!!!!
            1 Берут животное на подарок ребенку или члену семьи (бывают исключения).
            2 Наличие маленьких детей и отсутствие опыта содержания собак и одиноким молодым мамам тоже откажем.
            3 Съемное жилье.
            4 Если люди раздраженно или грубо отвечают на вопросы.
            5 Если люди, еще не взяв собаку, просят у нас и поводок и ошейник и цепь и будку, так как у них нет денег на это.
            6 Если у человека было много животных и человек не скажет, причины их исчезновения
            7 Лояльное отношение к самовыгулу собак.
            8 Если люди передаривали своих бывших животных по просьбе друзей/родственников.
            9 Если люди с легкостью и не задумываясь отвечают на все наши вопросы и согласны на все наши условия.
             при этом не задавая своих вопросов (чаще это молодые люди в возрасте 18 + лет).
            10 Если после передачи собаки выяснится, что люди ввели нас в заблуждение (обманули), то животное мы забираем обратно.
            """;
    public static final String POTENTIAL_ANIMAL_OWNER_NEW_CONTACT = " Вам нужно оставить номер для дальнейших контактов,\nформа записи номера 8 (87978)-90-90-7979.";
    // NEW_USER_MESSAGE_VOLUNTEER позвать волонтёра
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Ведение питомца
    public static final String PET_MANAGEMENT_FILL_IN_REPORT = " Заполните  форму ежедневного отчета";

    public static final String INCORRECT_REPORT_MESSAGE = "Вы прислали некорректно заполненный отчет. Форма заполнения отчета:";
    public static final String EXAMPLE_CORRECT_REPORT_MESSAGE = """
            Пожалуйста, пришлите фото питомца и ответы на следующие вопросы в одном сообщении:
            1. Опишите рацион питомца?
            2. Опишите как чувствует себя питомец и как привыкает к новому месту?
            3. Опишите изменение в поведении: отказ от старых привычек, приобретение новых?
""";
    public static final String PET_MANAGEMENT_CAUTION_ON_REPORT = "Дорогой усыновитель, мы заметили, что ты заполняешь отчет не так подробно, как необходимо. Пожалуйста, подойди ответственнее "
                                    + "к этому занятию. В противном случае волонтеры приюта будут обязаны самолично проверять условия содержания собаки";
    public static final String PET_MANAGEMENT_FINAL = "Вы успешно прошли  испытательный срок, Поздравляем Вас!!!!! теперь на Вас большая ответственность не подведите.";
    public static final String PET_MANAGEMENT_DOP_TIME = " Вам назначен дополнительный испытательный срок ";
    public static final String PET_MANAGEMENT_DID_NOT_COPE = " Вы не справились извините,сделайте выводы и подумайте над поведением.";


}
