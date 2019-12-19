
import org.terasology.engine.SimpleUri;
import org.terasology.math.ChunkMath;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.In;
import org.terasology.world.generation.BaseFacetedWorldGenerator;
import org.terasology.world.generation.WorldBuilder;
import org.terasology.world.generation.facets.SurfaceHeightFacet;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.generator.plugin.WorldGeneratorPluginLibrary;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import org.terasology.math.geom.BaseVector3i;
import org.terasology.registry.CoreRegistry;
import org.terasology.utilities.procedural.WhiteNoise;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.CoreChunk;
import org.terasology.world.generation.Region;
import org.terasology.world.generation.WorldRasterizer;

import java.util.List;
import java.util.Map;


public class TutorialWorldRasterizer implements WorldRasterizer {
    private Block water;
    private Block ice;
    private Block stone;
    private Block sand;
    private Block grass;
    private Block snow;
    private Block dirt;

    @Override
    public void initialize() {
        BlockManager blockManager = CoreRegistry.get(BlockManager.class);

        stone = blockManager.getBlock("CoreBlocks:Stone");
        water = blockManager.getBlock("CoreBlocks:Water");
        ice = blockManager.getBlock("CoreBlocks:Ice");
        sand = blockManager.getBlock("CoreBlocks:Sand");
        grass = blockManager.getBlock("CoreBlocks:Grass");
        snow = blockManager.getBlock("CoreBlocks:Snow");
        dirt = blockManager.getBlock("CoreBlocks:Dirt");
    }

    @Override
    public void generateChunk(CoreChunk chunk, Region chunkRegion) {
        SurfaceHeightFacet surfaceHeightFacet = chunkRegion.getFacet(SurfaceHeightFacet.class);
        for (Vector3i position : chunkRegion.getRegion()) {
            float surfaceHeight = surfaceHeightFacet.getWorld(position.x, position.z);
            if (position.y < surfaceHeight) {
                chunk.setBlock(ChunkMath.calcBlockPos(position), snow);
            }
        }
    }
}