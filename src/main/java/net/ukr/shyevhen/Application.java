package net.ukr.shyevhen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.ukr.shyevhen.controllers.BasketCleaner;
import net.ukr.shyevhen.model.Genre;
import net.ukr.shyevhen.model.Role;
import net.ukr.shyevhen.model.ShopUser;
import net.ukr.shyevhen.service.AuthorService;
import net.ukr.shyevhen.service.BasketService;
import net.ukr.shyevhen.service.BookService;
import net.ukr.shyevhen.service.GenreService;
import net.ukr.shyevhen.service.OrderService;
import net.ukr.shyevhen.service.UserService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner test(final UserService userService, final AuthorService authorService, final BookService bookService, 
			final OrderService orderService, final BasketService basketService, final GenreService genreService) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				BasketCleaner bc = new BasketCleaner(basketService);
				if(genreService.countGenres() == 0) {
					addGenres();
				}
				if(!userService.existByUserLogin("Admin")) {
					BCryptPasswordEncoder e = new BCryptPasswordEncoder();
					String pass = e.encode("password");
					ShopUser admin = new ShopUser("Admin", pass, "Admin", "Main", "my_email@gmail.com", "+380113223232", Role.ADMIN);	
					userService.addUser(admin);
				}
			}
			
			private void addGenres() {
				Genre genre = null;
				addBusinessEconomicsLegal(genre);
				addChildren(genre);
				addDocumentary(genre);
				addHouseLifeHobby(genre);
				addArtCultureReligion(genre);
				addComputer(genre);
				addScinceTechnologyMedicine(genre);
				addEducationReference(genre);
				addFiction(genre);
			}
			private void addBusinessEconomicsLegal(Genre genre) {
				genre = new Genre("/books/business-economics-legal", "Бизнес, экономика и право");
				genreService.addGenre(genre);
				genre = new Genre("/books/business-economics-legal/business-psychology", "Бизнес и психология");
				genreService.addGenre(genre);
				genre = new Genre("/books/business-economics-legal/bookkeeping", "Бухгалтерия");
				genreService.addGenre(genre);
				genre = new Genre("/books/business-economics-legal/logistics", "Логистика");
				genreService.addGenre(genre);
				genre = new Genre("/books/business-economics-legal/marketing", "Маркетинг");
				genreService.addGenre(genre);
				genre = new Genre("/books/business-economics-legal/banking", "Банковское дело");
				genreService.addGenre(genre);
				genre = new Genre("/books/business-economics-legal/economy", "Экономика");
				genreService.addGenre(genre);
				genre = new Genre("/books/business-economics-legal/administrative-law", "Административное право");
				genreService.addGenre(genre);
				genre = new Genre("/books/business-economics-legal/civil-law", "Гражданское право");
				genreService.addGenre(genre);
				genre = new Genre("/books/business-economics-legal/constitutional-law", "Конституционное право");
				genreService.addGenre(genre);
			}
			
			private void addChildren(Genre genre) {
				genre = new Genre("/books/children", "Для детей");
				genreService.addGenre(genre);
				genre = new Genre("/books/children/encyclopedia", "Энциклопедии");
				genreService.addGenre(genre);
				genre = new Genre("/books/children/for-reading", "Для чтения");
				genreService.addGenre(genre);
				genre = new Genre("/books/children/fairytales-myths-legends", "Сказки, мифы и легенды");
				genreService.addGenre(genre);
				genre = new Genre("/books/children/fantasy", "Фантастика");
				genreService.addGenre(genre);
				genre = new Genre("/books/children/detective", "Детективы");
				genreService.addGenre(genre);
				genre = new Genre("/books/children/adventure", "Приключения");
				genreService.addGenre(genre);
			}
			
			private void addDocumentary(Genre genre) {
				genre = new Genre("/books/documentary", "Документальные");
				genreService.addGenre(genre);
				genre = new Genre("/books/documentary/biography", "Биорафии");
				genreService.addGenre(genre);
				genre = new Genre("/books/documentary/memoirs", "Мемуары");
				genreService.addGenre(genre);
				genre = new Genre("/books/documentary/journalism", "По публицистике");
				genreService.addGenre(genre);
			}
			
			private void addHouseLifeHobby(Genre genre) {
				genre = new Genre("/books/house-lifestyle-hobby", "Дом, досуг и хобби");
				genreService.addGenre(genre);
				genre = new Genre("/books/house-lifestyle-hobby/pets", "Домашние животные");
				genreService.addGenre(genre);
				genre = new Genre("/books/house-lifestyle-hobby/needlework", "Рукоделие");
				genreService.addGenre(genre);
				genre = new Genre("/books/house-lifestyle-hobby/cookery", "Готовка");
				genreService.addGenre(genre);
				genre = new Genre("/books/house-lifestyle-hobby/gardening", "Садоводство");
				genreService.addGenre(genre);
			}
			
			private void addArtCultureReligion(Genre genre) {
				genre = new Genre("/books/art-culture-religion", "Искусство, культура и религия");
				genreService.addGenre(genre);
				genre = new Genre("/books/art-culture-religion/religion", "Религия");
				genreService.addGenre(genre);
				genre = new Genre("/books/art-culture-religion/architecture", "Архитектура");
				genreService.addGenre(genre);
				genre = new Genre("/books/art-culture-religion/art", "Искусство");
				genreService.addGenre(genre);
				genre = new Genre("/books/art-culture-religion/cinematography-photo-theater", "Кино, фото и театр");
				genreService.addGenre(genre);
				genre = new Genre("/books/art-culture-religion/culture-civilization", "Культура и цивилизации");
				genreService.addGenre(genre);
				genre = new Genre("/books/art-culture-religion/music", "Музыка");
				genreService.addGenre(genre);
			}
			
			private void addComputer(Genre genre) {
				genre = new Genre("/books/computer", "Компьютер");
				genreService.addGenre(genre);
				genre = new Genre("/books/computer/database", "Базы данных");
				genreService.addGenre(genre);
				genre = new Genre("/books/computer/1c", "1С");
				genreService.addGenre(genre);
				genre = new Genre("/books/computer/graphics-design", "Графический дизайн");
				genreService.addGenre(genre);
				genre = new Genre("/books/computer/programming", "Программирование");
				genreService.addGenre(genre);
				genre = new Genre("/books/computer/os", "Операционные системы");
				genreService.addGenre(genre);
				genre = new Genre("/books/computer/computer-networks", "Компьютерные сети");
				genreService.addGenre(genre);
			}
			
			private void addScinceTechnologyMedicine(Genre genre) {
				genre = new Genre("/books/science-technology-medicine", "Наука, технология и медицина");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/humanities-social-sciences", "Гуманитарные и общественные науки");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/humanities-social-sciences/warfare", "Военное дело");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/humanities-social-sciences/policy", "Политика");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/humanities-social-sciences/sociology", "Социология");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/humanities-social-sciences/philosophy", "Философия");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/humanities-social-sciences/history", "История");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/humanities-social-sciences/psychology", "Психология");
				genreService.addGenre(genre);
				addSTMPartTwo(genre);
			}
			
			private void addSTMPartTwo(Genre genre) {
				genre = new Genre("/books/science-technology-medicine/applied-natural-sciences", "Прикладные и естественные науки");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/applied-natural-sciences/communications", "Связь");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/applied-natural-sciences/medicine", "Медицина");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/applied-natural-sciences/earth-science", "Науки о земле");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/applied-natural-sciences/building", "Строительство");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/applied-natural-sciences/transport", "Транспорт");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/applied-natural-sciences/transport/cars", "Машины");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/applied-natural-sciences/transport/ships", "Корабли");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/applied-natural-sciences/transport/planes", "Самолеты");
				genreService.addGenre(genre);
				genre = new Genre("/books/science-technology-medicine/applied-natural-sciences/transport/others", "Прочий транспорт");
				genreService.addGenre(genre);
			}
			
			private void addEducationReference(Genre genre) {
				genre = new Genre("/books/educational-reference", "Справочники и образование");
				genreService.addGenre(genre);
				genre = new Genre("/books/educational-reference/preschool", "Дошкольное образование");
				genreService.addGenre(genre);
				genre = new Genre("/books/educational-reference/school", "Для школьников");
				genreService.addGenre(genre);
				genre = new Genre("/books/educational-reference/dictionaries-reference", "Словари и справочники");
				genreService.addGenre(genre);
			}
			
			private void addFiction(Genre genre) {
				genre = new Genre("/books/fiction", "Художественная литература");
				genreService.addGenre(genre);
				genre = new Genre("/books/fiction/folklore", "Фолькльор");
				genreService.addGenre(genre);
				genre = new Genre("/books/fiction/classic", "Классика");
				genreService.addGenre(genre);
				genre = new Genre("/books/fiction/detectives-action-thrillers", "Детективы, боевики и триллеры");
				genreService.addGenre(genre);
				genre = new Genre("/books/fiction/historical-adventure-novels", "Исторические и приключенческие романы");
				genreService.addGenre(genre);
				genre = new Genre("/books/fiction/fantastic", "Фантастика");
				genreService.addGenre(genre);
			}
		};
	}
}
