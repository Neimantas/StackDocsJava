package Models.Const;

public class Settings {
	/**
	 * Needed when generating new ExampleId. Ensures that ids will be unique
	 */
	public static final int DEFAULT_NUMBER_FOR_EXAMPLES_IDS = 10000;
	/**
	 * Needed when generating new LanguageTagsId. Ensures that ids will be unique
	 */
	public static final int DEFAULT_NUMBER_FOR_LANGUAGE_IDS = 60;
	/**
	 * Needed for start searching for unused Topic Id
	 */
	public static final int FIRST_ID_IN_A_DATABASE_TABLE = 1;
	/**
	 * Determines how many topics will be displayed on one page
	 */
	public static final int NUMBER_OF_TOPICS_PER_PAGE = 10;
	/**
	 * Time in seconds
	 */
	public static final long CACHE_DATA_DEFAULT_EXPIRE_TIME = 1000;
	/**
	 * One second equals 1000 miliseconds 
	 */
	public static final int ONE_SECOND = 1000;
}
