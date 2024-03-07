import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Human {
    @NonNull
    final String name;
    @NonNull
    final String surname;
    @NonNull
    final String patronymic;
    @NonNull
    final Gender gender;
    Human father;
    Human mother;
    List<Human> children;
    //List<Human> childrenList;

    private String nameFormater(String string) {
        return StringUtils.capitalize(StringUtils.lowerCase(string));
    }

    public Human(@NonNull String name, @NonNull String surname,
                 @NonNull String patronymic, @NonNull Gender gender) {
        this.name = nameFormater(name);
        this.surname = nameFormater(surname);
        this.patronymic = nameFormater(patronymic);
        this.gender = gender;
    }
    
    public void setParents(Human mother, Human father) throws Exception {
        if (mother.getGender().equals(father.getGender())) {
            throw new Exception("у родителей одинаковый пол");
        }
        this.father = father;
        this.mother = mother;
        mother.children.add(this);
        father.children.add(this);
    }

    public Human makeChild(String name, String surname, String patronymic,
                          Gender gender, Human human) throws Exception {
        if (human.getGender().equals(this.gender)) {
            throw new Exception("у родителей одинаковый пол");
        }
        setParents(this, human);
        return new Human(name, surname, patronymic,gender);
    }

    public String returnFullName() {
        return surname + " " + name + " " + patronymic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return gender == human.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender);
    }
}
/*
2. Внутри проекта создать модуль - Human.
2.1 Данный модуль будет отвечать за сущность "Человек".
Человек должен иметь следующие атрибуты: имя, фамилию, отчество, пол, отец, мать, дети.
Из всех атрибутов, только поля "дети", "отец", "мать" могут быть null.
Передаваемое Фио должно преобразовываться в формат первая заглавная буква,
все остальные буквы в нижнем регистре. (смотри методы из класса StringUtils из commons-lang3).
2.2. Создать функцию указание родителей: передаются два человека,
которые при этом являются людьми разного пола.
У отца и матери добавляется ребенок. У ребенка родители.
2.3 У человека есть функция "произвести ребенка":
на вход имя, фамилия, отчество, пол, еще один человек -
на выход новый человек.
2.3.1 Проверить что, переданный на вход человек, является лицом противоположного пола
 от человека у которого вызывают рождение.
2.3.2 У ребенка и родителей должны быть установлены родственные связи (2.3 пункт).
2.4 У человека есть функция "Получить полное имя": возвращается строка ФИО.
 */