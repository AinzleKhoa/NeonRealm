SELECT 
    g.game_id AS id, 
    g.title, 
	g.description,
	g.release_date,
    g.image_url, 
    g.price, 
	-- Subquery to remove duplicates before aggregation (Developers)
	(SELECT STRING_AGG(unique_developers.name, ', ')
	FROM (SELECT DISTINCT d.name
		  FROM Game_Developers gd
		  JOIN Developers d ON d.developer_id = gd.developer_id
		  WHERE g.game_id = gd.game_id) AS unique_developers
	) AS developers,
	-- Subquery to remove duplicates before aggregation (Publishers)
	(SELECT STRING_AGG(unique_publishers.name, ', ')
	FROM (SELECT DISTINCT p.name
		  FROM Game_Publishers gp
		  JOIN Publishers p ON p.publisher_id = gp.publisher_id
		  WHERE g.game_id = gp.game_id) AS unique_publishers
	) AS publishers,
	-- Subquery to remove duplicates before aggregation (Genres)
	(SELECT STRING_AGG(unique_genres.name, ', ')
	FROM (SELECT DISTINCT gs.name
		  FROM Game_Genres gg
		  JOIN Genres gs ON gs.genre_id = gg.genre_id
		  WHERE g.game_id = gg.game_id) AS unique_genres
	) AS genres,
    -- Subquery to remove duplicates before aggregation (Platforms)
    (SELECT STRING_AGG(unique_platforms.name, ', ') 
     FROM (SELECT DISTINCT p.name 
           FROM Game_Platforms gp 
           JOIN Platforms p ON p.platform_id = gp.platform_id 
           WHERE gp.game_id = g.game_id) AS unique_platforms
    ) AS platforms,
    -- Subquery to remove duplicates before aggregation (Categories)
    (SELECT STRING_AGG(unique_categories.name, ', ') 
     FROM (SELECT DISTINCT c.name 
           FROM Game_Categories gc 
           JOIN Categories c ON c.category_id = gc.category_id 
           WHERE gc.game_id = g.game_id) AS unique_categories
    ) AS categories
FROM Games g
ORDER BY    g.game_id, 
			g.title, 
			g.description,
			g.release_date,
			g.image_url, 
			g.price
OFFSET ? ROWS
FETCH NEXT ? ROWS ONLY